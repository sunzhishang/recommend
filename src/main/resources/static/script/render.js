function renderList(list) {
    for (let i = 0; i < list.length; i++) {
        console.log(list[i].grade)
        let id = list[i].idStr
        let title = list[i].title
        let imageUrl = list[i].image
        let heartStr = "<span id='heart-" + id + "' class='glyphicon glyphicon-heart-empty' onclick='pinArticle(" + '"' + id + '"' + ")'></span>"
        let starStrs = ["<span id='start-" + id + "-1'" + " class='glyphicon glyphicon-star-empty' onclick='gradeArticle(" + '"' + id + '"' + ",1)'></span>",
            "<span id='start-" + id + "-2'" + " class='glyphicon glyphicon-star-empty' onclick='gradeArticle(" + '"' + id + '"' + ",2)'></span>",
            "<span id='start-" + id + "-3'" + " class='glyphicon glyphicon-star-empty' onclick='gradeArticle(" + '"' + id + '"' + ",3)'></span>",
            "<span id='start-" + id + "-4'" + " class='glyphicon glyphicon-star-empty' onclick='gradeArticle(" + '"' + id + '"' + ",4)'></span>",
            "<span id='start-" + id + "-5'" + " class='glyphicon glyphicon-star-empty' onclick='gradeArticle(" + '"' + id + '"' + ",5)'></span>"]
        if (list[i].pined === true) {
            heartStr = heartStr.replaceAll("glyphicon glyphicon-heart-empty", "glyphicon glyphicon-heart")
            heartStr = heartStr.replaceAll("pinArticle", "cancelPinArticle");
        }
        for (let j = 0; j < list[i].grade; j++) {
            starStrs[j] = starStrs[j].replaceAll("glyphicon glyphicon-star-empty", "glyphicon glyphicon-star")
        }

        if (!$("#" + id)[0]) {
            let articleStr =
                ' <div style="display: flex;-webkit-box-align: center;' +
                '    align-items: center;-webkit-box-pack: justify;width: 100%;height: 124px;padding: 12px 0;' +
                '    cursor: pointer;">' +
                ' <a onclick="clickArticle(' + "'" + id + "'" + ')"  id="' + id + '" class="article" href="https://www.dongchedi.com/article/' + id + '"' +
                ' style="align-items: center;display: flex;background-color: rgb(255, 255, 255);border-bottom: 1px solid rgb(230, 230, 230);margin-left: 250px;margin-top: 20px;text-decoration: none">' +
                '        <div class="imgDiv" style="position: relative;width: 150px;height: 100px;margin-right: 16px;">' +
                '            <img style="width: 150px;height: 100px;"' +
                '                src="' + imageUrl + '" alt="网络出错了"' + '/>' +
                '       </div>' +
                '        <div class="titleDiv" style="width:500px;flex-grow: 1;flex-shrink: 1;flex-basis: 0;display: block; color: #1a1a1a; font-family: PingFang SC,Hiragino Sans GB,Microsoft YaHei,Helvetica,Arial; font-size: 20px;">' + title +
                '        </div>' +
                ' </a>' +
                ' <div class="user-related" style="margin-left: 20px">' + heartStr


            for (let i = 0; i < starStrs.length; i++) {
                articleStr += starStrs[i]
            }
            articleStr += ' </div> </div>'

            $("#list").append(articleStr);
        }
    }
}

function cancelPinArticle(articleId) {
    $.ajax({
        type: 'get',
        url: "/motor/behavior/cancel_pin",
        data: {
            "article_id": articleId
        },
        contentType: "application/x-www-form-urlencoded",
        success: function (resp) {
            if (resp.code === 5000) {
                alert("用户未登陆")
                return;
            }
            $("#heart-" + articleId).removeClass("glyphicon-heart").addClass("glyphicon-heart-empty").attr("onclick", 'pinArticle("' + articleId + '")');
        }
    });
}

function pinArticle(articleId) {
    $.ajax({
        type: 'get',
        url: "/motor/behavior/pin",
        data: {
            "article_id": articleId
        },
        contentType: "application/x-www-form-urlencoded",
        success: function (resp) {
            if (resp.code === 5000) {
                alert("用户未登陆")
                return;
            }
            $("#heart-" + articleId).removeClass("glyphicon-heart-empty").addClass("glyphicon-heart").attr("onclick", 'cancelPinArticle("' + articleId + '")');
        }
    });
}

function gradeArticle(articleId, grade) {
    $.ajax({
        type: 'get',
        url: "/motor/behavior/grade",
        contentType: "application/x-www-form-urlencoded",
        data: {
            "article_id": articleId,
            "grade": grade
        },
        success: function (resp) {
            if (resp.code === 5000) {
                alert("用户未登陆")
                return;
            }
            for (let i = 0; i < grade; i++) {
                $("#start-" + articleId + "-" + (i + 1)).removeClass("glyphicon glyphicon-star-empty").addClass("glyphicon glyphicon-star")
            }
            for (let i = grade + 1; i <= 5; i++) {
                $("#start-" + articleId + "-" + i).removeClass("glyphicon glyphicon-star").addClass("glyphicon glyphicon-star-empty")
            }
        }
    });
}

function clickArticle(articleId) {
    $.ajax({
        type: 'get',
        url: "/motor/behavior/click",
        data: {
            "article_id": articleId
        }
    });
}

function retrieveArticles(pageNo) {
    let keywords = $(".input").val();
    //发送ajax请求
    $.get('/motor/article/search', {
        "keywords": keywords,
        "pageNo": pageNo,
        "pageSize": 10
    }, function (resp) {
        renderList(resp.data)
    })
}

