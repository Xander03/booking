import {getFormData, isValidFormData, showError} from "../utils/form.js";
import {redirect} from "../utils/redirector.js";
import {HOME, SIGN_IN} from "../settings/URLs.js";
import {SIGN_UP_URL} from "../settings/serverEndoints.js";
import {post} from "../api/api.js";

const user = localStorage.getItem('user');
if (user !== null && user !== '') {
    redirect(HOME);
}

$(document).ready(function () {
    $('#link').on('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        redirect(SIGN_IN);
        return false;
    });
    $('#submit').on('click', function (e) {
        e.preventDefault();
        e.stopPropagation();
        const form = $('#form');
        if (isValidFormData(form)) {
            post(SIGN_UP_URL, getFormData(form), success, showError);
        }
        return false;
    })
});

function success(data) {
    redirect(HOME);
}