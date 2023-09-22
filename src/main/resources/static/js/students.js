function detailsBtn(id) {
    let newWindow = window.open("/students/" + id, "_blank");
    newWindow.focus();
}

function editBtn(id) {
    let newWindow = window.open("/students/" + id, "_blank");
    newWindow.focus();
    newWindow.onload = function () {
        newWindow.document.getElementById("edit-button").click();
    }
}