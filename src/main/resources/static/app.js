var stompClient = null;

function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/echopostapp', function (serverMessage) {
            showServerMessage(JSON.parse(serverMessage.body).content);
        });
    });
}

function showServerMessage(serverMessage) {
    $("#server-message").html("<tr><td>" + serverMessage + "</td></tr>");
}


$(function () {
    $(document).ready(function() {
        connect();
    });
});

