let datepicker = document.getElementById("datepicker");
let dateBth = document.getElementById("date-btn");
let colTwo = document.getElementsByClassName("column-two")[0];

function loadStudentsAttendance() {
    console.log(datepicker.value);
    colTwo.style.display = 'flex';
}

function saveStudentsAttendance() {
    alert('attendance saved');
}