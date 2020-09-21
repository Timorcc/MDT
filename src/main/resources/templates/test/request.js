document.write("<script language=javascript src='https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js'></script>");

// var base = "http://www.binhub.top:8080";
var base = "http://localhost:8080";

function sendRequest(url, method, data, callback) {
    $.ajax({
        url: base + url,
        type: method,
        "Access-Control-Allow-Origin": "*",
        data: data,
        success: function(res) {
            if (callback) callback(res);
        },
        error: function(res) {
            console.log("error");
            console.log(res);
        }

    })
}

function sendMultipartRequest(url, method, data, callback) {
    $.ajax({
        url: base + url,
        type: method,
        "Access-Control-Allow-Origin": "*",
        async: false,
        processData: false,   // jQuery不要去处理发送的数据
        contentType: false,   // jQuery不要去设置Content-Type请求头
        data: data,
        success: function(res) {
            if (callback) callback(res);
        },
        error: function(res) {
            console.log("error");
            console.log(res);
        }

    })
}