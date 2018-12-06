import {get, post, put} from "../api/api.js";
import {ROOM_URL} from "../settings/serverEndoints.js";
import {isValidFormData, showError} from "../utils/form.js";
import {redirect} from "../utils/redirector.js";
import {ROOM_TARGET} from "../settings/URLs.js";

const hotelId = new URLSearchParams(window.location.search).get('hotel');

$(document).ready(function () {
    const id = new URLSearchParams(window.location.search).get('id');
    if (id !== null && id !== '') {
        get(`${ROOM_URL}/${id}`, initEditForm, showError);
    } else {
        initAddForm();
    }
});

function initEditForm(room) {
    $('#form').find('input')
        .each(function () {
            $(this).val(room[$(this).attr('id')]);
            addInputHandler($(this), room);
        });
    $('#hotel').val(room.hotel.id);
    $('#submit').on('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        const form = $('#form');
        if (isValidFormData(form)) {
            put(ROOM_URL, room, (room) => redirect(`${ROOM_TARGET}?id=${room.id}`), showError);
        }
        return false;
    })
}

function initAddForm() {
    const room = {
        hotel: {
            id: hotelId
        }
    };
    $('#form').find('input')
        .each(function () {
            addInputHandler($(this), room);
        });
    $('#hotel').val(room.hotel.id);
    $('#submit').on('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        const form = $('#form');
        if (isValidFormData(form)) {
            post(ROOM_URL, room, (room) => redirect(`${ROOM_TARGET}?id=${JSON.parse(room).id}`), showError);
        }
        return false;
    })
}

function addInputHandler(input, room) {
    input.on('change', function () {
        room[input.attr('id')] = input.val();
    })
}