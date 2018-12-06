import {showError} from "../utils/form.js";
import {redirect, goBack} from "../utils/redirector.js";
import {HOME, ROOM_FORM} from "../settings/URLs.js";
import {get, remove, patch} from "../api/api.js";
import {ROOM_URL} from "../settings/serverEndoints.js";
import {getUser} from "../utils/local_storage.js";

const id = new URLSearchParams(window.location.search).get('id');
if (id === null || id === '') {
    redirect();
}

$(document).ready(function () {
    get(`${ROOM_URL}/${id}`, function (room) {
        showRoom(room);
        initReserveButton(room);
        initUnreserveButton(room);
    }, showError);
    initEditButton();
    initDeleteButton();
});

function showRoom(room) {
    $('#room').find('label')
        .each(function() {
            const id = $(this).attr('id');
            if (id !== null && id !== '') {
                $(this).text(room[id]);
            }
        });
    console.log(room.hotel.name);
    $('#room #hotel').text(room.hotel.name);
}

function initEditButton() {
    const edit = $('#edit');
    if (getUser().roles.includes('ADMIN')) {
        edit.on('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            redirect(`${ROOM_FORM}?id=${id}`);
            return false;
        });
    } else {
        edit.css('display', 'none');
    }
}

function initDeleteButton() {
    const deleteButton = $('#delete');
    if (getUser().roles.includes('ADMIN')) {
        deleteButton.on('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            remove(`${ROOM_URL}/${id}`, () => goBack(), showError);
            return false;
        });
    } else {
        deleteButton.css('display', 'none');
    }
}

function initReserveButton(room) {
    const userId = getUser().id;
    const button = $('#reserve');
    if (room.user && room.user.id === userId) {
        button.css('display', 'none');
    } else {
        button.on('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            patch(`${ROOM_URL}/${id}/reserve/${getUser().id}`, () => redirect(HOME), showError);
            return false;
        });
    }
}

function initUnreserveButton(room) {
    const userId = getUser().id;
    const button = $('#unreserve');
    if (room.user && room.user.id === userId) {
        button.on('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            patch(`${ROOM_URL}/${id}/unreserve`, () => redirect(HOME), showError);
            return false;
        });
    } else {
        button.css('display', 'none');
    }
}