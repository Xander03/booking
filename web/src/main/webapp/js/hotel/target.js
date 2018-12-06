import {showError} from "../utils/form.js";
import {redirect} from "../utils/redirector.js";
import {HOTEL_LIST, HOTEL_FORM, ROOM_FORM} from "../settings/URLs.js";
import {get, remove} from "../api/api.js";
import {HOTEL_URL} from "../settings/serverEndoints.js";
import {getUser} from "../utils/local_storage.js";

const id = new URLSearchParams(window.location.search).get('id');
if (id === null || id === '') {
    redirect(HOTEL_LIST);
}

$(document).ready(function () {
    get(`${HOTEL_URL}/${id}`, showHotel, showError);
    initEditButton();
    initDeleteButton();
    initAddNewRoomButton();
});

function showHotel(hotel) {
    $('#hotel').find('label')
        .each(function() {
            const id = $(this).attr('id');
            if (id !== null && id !== '') {
                $(this).text(hotel[id]);
            }
        })
}

function initEditButton() {
    const edit = $('#edit');
    if (getUser().roles.includes('ADMIN')) {
        edit.on('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            redirect(`${HOTEL_FORM}?id=${id}`);
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
            remove(`${HOTEL_URL}/${id}`, () => redirect(HOTEL_LIST), showError);
            return false;
        });
    } else {
        deleteButton.css('display', 'none');
    }
}

function initAddNewRoomButton() {
    const addNewButton = $('#add_room');
    if (getUser().roles.includes('ADMIN')) {
        addNewButton.on('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            redirect(`${ROOM_FORM}?hotel=${id}`);
            return false;
        });
    } else {
        addNewButton.css('display', 'none');
    }
}