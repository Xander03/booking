import {get} from "../api/api.js";
import {ROOM_FREE_URL} from "../settings/serverEndoints.js";
import {showError} from "../utils/form.js";
import {redirect} from "../utils/redirector.js";
import {ROOM_TARGET} from "../settings/URLs.js";
import {getUser} from "../utils/local_storage.js";

$(document).ready(function () {
    init();
});

function init() {
    get(`${ROOM_FREE_URL}/${getUser().id}`, (rooms) => {
        showRooms(rooms);
        initTargetLinks();
    }, showError);
}

function showRooms(rooms) {
    if (rooms.length > 0) {
        let temp = '<ul>';

        temp += rooms.map(room =>
            `<li id="${room.id}">` +
                `<p>${room.id}</p>` +
                `<p>${room.floor}</p>` +
                `<p>${room.places}</p>` +
            `</li>`
        );

        temp += '</ul>';

        $('#rooms').html(temp);
    }
}

function initTargetLinks() {
    $('li').each(function () {
        $(this).on('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            redirect(`${ROOM_TARGET}?id=${$(this).attr('id')}`);
            return false;
        })
    })
}