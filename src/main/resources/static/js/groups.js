function groupClick(id) {
    let newWindow = window.open("/groups/" + id, "_blank");
    newWindow.focus();
}