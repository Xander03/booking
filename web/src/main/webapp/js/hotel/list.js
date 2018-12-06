import {get} from "../api/api.js";
import {HOTEL_URL} from "../settings/serverEndoints.js";
import {showError} from "../utils/form.js";
import {redirect} from "../utils/redirector.js";
import {HOTEL_TARGET, HOTEL_FORM} from "../settings/URLs.js";
import {getUser} from "../utils/local_storage.js";

$(document).ready(function () {
    init();
});

function init() {
    get(HOTEL_URL, (hotels) => {
        showHotels(hotels);
        initTargetLinks();
        initAddNewButton();
    }, showError);
}

function showHotels(hotels) {
    if (hotels.length > 0) {
        let temp = '<ul>';

        temp += hotels.map(hotel =>
            `<li id="${hotel.id}">` +
                `<p>${hotel.name}</p>` +
                `<p>${hotel.address}</p>` +
            `</li>`
        );

        temp += '</ul>';

        $('#hotels').html(temp);
    }
}

function initTargetLinks() {
    $('li').each(function () {
        $(this).on('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            redirect(`${HOTEL_TARGET}?id=${$(this).attr('id')}`);
            return false;
        })
    })
}

function initAddNewButton() {
    const add = $('#add');
    if (getUser().roles.includes('ADMIN')) {
        add.on('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            redirect(HOTEL_FORM);
            return false;
        })
    } else {
        add.css('display', 'none');
    }
}