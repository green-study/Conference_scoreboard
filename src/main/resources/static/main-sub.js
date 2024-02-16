const socket = new SockJS('/ws-endpoint');
const stompClient = Stomp.over(socket);

stompClient.connect({}, function () {
    stompClient.subscribe('/topic/message-updates', function (response) {
        const message = JSON.parse(response.body);
        location.reload();
    });
});