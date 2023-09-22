let fields = document.getElementsByClassName("field-input");
let sb = document.getElementById("save-button");
let edb = document.getElementById("edit-button");
let exb = document.getElementById("exit-button");
let db = document.getElementById("delete-button");

function editStudentBtn() {
    for (let i = 0; i < fields.length; i++) {
        fields[i].disabled = false;
    }

    edb.classList.add("inactive");
    db.classList.add("inactive");
    exb.classList.remove("inactive");
    sb.classList.remove("inactive");
}

function exitStudentBtn() {
    location.reload();
}

function saveStudentBtn() {
    alert('Save alert');
    for (let i = 0; i < fields.length; i++) {
        fields[i].disabled = true;
    }

    edb.classList.remove("inactive");
    db.classList.remove("inactive");
    exb.classList.add("inactive");
    sb.classList.add("inactive");
}



