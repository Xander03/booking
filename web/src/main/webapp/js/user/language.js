const id = new URLSearchParams(window.location.search).get('id');
const input = $('.footer #id');
if (id === null || id === '' || id === undefined) {
    input.remove();
} else {
    input.val(id);
}