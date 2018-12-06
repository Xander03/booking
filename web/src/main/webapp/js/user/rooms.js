import {get} from "../api/api.js";
import {GET_RESERVED_ROOMS} from "../settings/serverEndoints.js";
import {showError} from "../utils/form.js";
import {getUser} from "../utils/local_storage.js";
import {redirect} from "../utils/redirector.js";
import {ROOM_TARGET} from "../settings/URLs.js";

$(document).ready(function () {
    const userId = getUser().id;
    get(`${GET_RESERVED_ROOMS}/${userId}`, function(rooms) {
        showReservedRoom(rooms);
        initTargetLinks();
    }, showError);
});

function showReservedRoom(rooms) {
    if (rooms.length > 0) {
        let temp = '<ul>';

        temp += rooms.map(room =>
            `<li id="${room.id}">` +
            `<p>${room.id}</p>` +
            `<p>${room.hotel.name}</p>` +
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