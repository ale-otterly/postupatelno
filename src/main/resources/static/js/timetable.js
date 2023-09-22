function lessonClick(groupId) {
    let newWindow = window.open("/groups/" + groupId, "_blank");
    newWindow.focus();
}