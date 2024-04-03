// main.js

let timerInterval; // setInterval을 저장하기 위한 변수
let startTime; // 시작 시간을 저장하기 위한 변수
let running = false; // 타이머가 실행 중인지 여부를 저장하는 변수

// 타이머 시작 함수
function startTimer() {
    startTime = new Date();
    timerInterval = setInterval(updateTimer, 1000);
    running = true;
}

// 타이머 업데이트 함수
function updateTimer() {
    let currentTime = new Date();
    let elapsedTime = currentTime - startTime;
    let seconds = Math.floor((elapsedTime / 1000) % 60);
    let minutes = Math.floor((elapsedTime / 1000 / 60) % 60);
    let hours = Math.floor((elapsedTime / 1000 / 60 / 60) % 24);

    // 10보다 작은 경우 0을 추가하여 두 자리로 표시
    seconds = seconds < 10 ? "0" + seconds : seconds;
    minutes = minutes < 10 ? "0" + minutes : minutes;
    hours = hours < 10 ? "0" + hours : hours;

    // 타이머 업데이트
    document.getElementById("timer").textContent = hours + ":" + minutes + ":" + seconds;
}

// 타이머 정지 함수
function stopTimer() {
    clearInterval(timerInterval);
    running = false;
}

// 시작 버튼 클릭 시 이벤트 핸들러
document.getElementById("startButton").addEventListener("click", function() {
    if (!running) {
        startTimer();
    }
});

// 종료 버튼 클릭 시 이벤트 핸들러
document.getElementById("stopButton").addEventListener("click", function() {
    if (running) {
        stopTimer();
    }
});

function deleteSubject() {
    var selectedId = document.querySelector('#subjectselect').value;
    if(selectedId !== "") {
        var form = document.getElementById('deleteForm');
        form.action = '/timer/del/' + selectedId;
        form.method = 'POST';
        form.submit();
    } else {
        alert('과목을 선택해주세요.');
    }
}

function sendRequest() {
    var url = "timer";
    var xhr = new XMLHttpRequest();
    xhr.open("GET", url, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // 요청이 성공하면 여기에 처리 로직 추가
            console.log("GET 요청이 성공했습니다.");
        }
    };
    xhr.send();
}

window.onload = function() {
    // GET 요청을 보내고 싶은 URL을 지정합니다.
    var url = '/timer';
    
    // XMLHttpRequest 객체를 생성합니다.
    var xhr = new XMLHttpRequest();
    
    // 요청을 준비합니다.
    xhr.open('GET', url, true);
    
    // 요청이 완료되었을 때 실행되는 콜백 함수를 정의합니다.
    xhr.onload = function () {
        // 요청이 성공적으로 완료되었을 때 실행됩니다.
        if (xhr.status >= 200 && xhr.status < 300) {
            // 응답으로 받은 데이터를 처리합니다.
            console.log(xhr.responseText);
        } else {
            // 요청이 실패한 경우 에러 메시지를 출력합니다.
            console.error('Request failed:', xhr.statusText);
        }
    };
    
    // 요청을 보냅니다.
    xhr.send();
};