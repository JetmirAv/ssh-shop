// public/index.js

var socket = io.connect("https://192.168.0.11:5000", {
  secure: true,
  reconnect: true,
  rejectUnauthorized: false,
});

socket.emit("create-room", window.location.pathname);

// socket.on("add-users", function (data) {
//   for (var i = 0; i < data.users.length; i++) {
//     var el = document.createElement("div"),
//       id = data.users[i];

//     el.setAttribute("id", id);
//     el.innerHTML = id;
//     el.addEventListener("click", function () {
//       // TODO: We will create this method
//     });
//     document.getElementById("users").appendChild(el);
//   }
// });

socket.on("remove-user", function (id) {
  var div = document.getElementById(id);
  document.getElementById("users").removeChild(div);
});

var peerConnection =
  window.RTCPeerConnection ||
  window.mozRTCPeerConnection ||
  window.webkitRTCPeerConnection ||
  window.msRTCPeerConnection;

var sessionDescription =
  window.RTCSessionDescription ||
  window.mozRTCSessionDescription ||
  window.webkitRTCSessionDescription ||
  window.msRTCSessionDescription;

navigator.getUserMedia =
  navigator.getUserMedia ||
  navigator.webkitGetUserMedia ||
  navigator.mozGetUserMedia ||
  navigator.msGetUserMedia;

var pc = new peerConnection({
  iceServers: [
    {
      url: "stun:stun.services.mozilla.com",
      username: "somename",
      credential: "somecredentials",
    },
  ],
});

function createOffer() {
  pc.createOffer(function (offer) {
    pc.setLocalDescription(
      new sessionDescription(offer),
      function () {
        socket.emit("make-offer", { offer: offer });
      },
      error
    );
  }, error);
}

function error(err) {
  console.warn("Error", err);
}

socket.on("offer-made", function (data) {
  console.log({ "offer-made": data });

  if (data.socket != socket.id) {
    offer = data.offer;
    pc.setRemoteDescription(
      new sessionDescription(data.offer),
      function () {
        pc.createAnswer(function (answer) {
          pc.setLocalDescription(
            new sessionDescription(answer),
            function () {
              socket.emit("make-answer", {
                answer: answer,
                to: data.socket,
              });
            },
            error
          );
        }, error);
      },
      error
    );
  }
});

var answersFrom = {};

socket.on("answer-made", function (data) {
  console.log({ "answer-made": "answer-made" });

  if (data.socket !== socket.id) {
    pc.setRemoteDescription(
      new sessionDescription(data.answer),
      function () {
        if (!answersFrom[data.socket]) {
          createOffer();
          answersFrom[data.socket] = true;
        }
      },
      error
    );
  }
});

pc.onaddstream = function (obj) {
  var vid = document.createElement("video");
  vid.setAttribute("class", "video-small");
  vid.setAttribute("autoplay", "autoplay");
  vid.setAttribute("id", "video-small");
  document.getElementById("users-container").appendChild(vid);
  vid.srcObject = obj.stream;
};

navigator.getUserMedia(
  { video: true, audio: true },
  function (stream) {
    var video = document.querySelector("video");
    video.srcObject = stream;
    pc.addStream(stream);
    createOffer();
  },
  error
);
