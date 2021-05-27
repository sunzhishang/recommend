function renderList(list) {
    for (let i = 0; i < list.length; i++) {
        let id = list[i].idStr
        let title = list[i].title
        let imageUrl = list[i].image
        let heartStr = ""
        let starStr = ""
        if (!$("#" + id)[0]) {
            let articleStr = ' <a id="' + id + '" class="article" href="https://www.dongchedi.com/article/' + id + '"' +
                ' style="display: flex;-webkit-box-align: center;' +
                '    align-items: center;-webkit-box-pack: justify;justify-content: space-between;width: 70%;height: 124px;padding: 12px 0;' +
                '    cursor: pointer;background-color: rgb(255, 255, 255);border-bottom: 1px solid rgb(230, 230, 230);margin-left: 250px;margin-top: 20px;text-decoration: none">' +
                '        <div class="imgDiv" style="position: relative;width: 150px;height: 100px;margin-right: 16px;">' +
                '            <img style="width: 150px;height: 100px;"' +
                '                src="' + imageUrl + '" alt="网络出错了"' + '/>' +
                '       </div>' +
                '        <div class="titleDiv" style="flex-grow: 1;flex-shrink: 1;flex-basis: 0;display: block; color: #1a1a1a; font-family: PingFang SC,Hiragino Sans GB,Microsoft YaHei,Helvetica,Arial; font-size: 20px;">' + title +
                '        </div>' +
                '       <div class="user-related">' +
                '         <span class="glyphicon glyphicon-heart"></span>' +
                '         <span>'
            '       </div>' +
            '    </a>'
            $("#list").append(articleStr);
        }
    }
}

function pinArticle() {

}

function gradeArticle() {

}

function clickArticle() {

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

