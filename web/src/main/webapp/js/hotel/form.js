import {get, post, put} from "../api/api.js";
import {HOTEL_URL} from "../settings/serverEndoints.js";
import {isValidFormData, showError} from "../utils/form.js";
import {redirect} from "../utils/redirector.js";
import {HOTEL_TARGET} from "../settings/URLs.js";

$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');
    if (id !== null && id !== '') {
        get(`${HOTEL_URL}/${id}`, initEditForm, showError);
    } else {
        initAddForm();
    }
});

function initEditForm(hotel) {
    $('#form').find('input')
        .each(function () {
            $(this).val(hotel[$(this).attr('id')]);
            addInputHandler($(this), hotel);
        });
    $('#submit').on('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        const form = $('#form');
        if (isValidFormData(form)) {
            put(HOTEL_URL, hotel, (hotel) => redirect(`${HOTEL_TARGET}?id=${hotel.id}`), showError);
        }
        return false;
    })
}

function initAddForm() {
    const hotel = {};
    $('#form').find('input')
        .each(function () {
            addInputHandler($(this), hotel);
        });
    $('#submit').on('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        const form = $('#form');
        if (isValidFormData(form)) {
            post(HOTEL_URL, hotel, (hotel) => redirect(`${HOTEL_TARGET}?id=${JSON.parse(hotel).id}`), showError);
        }
        return false;
    })
}

function addInputHandler(input, hotel) {
    input.on('change', function () {
        hotel[input.attr('id')] = input.val();
    })
}