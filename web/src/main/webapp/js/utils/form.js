export const getFormData = (form) => {
    let data = {};
    getAllInputs(form)
        .each(function () {
            const val = $(this).val();
            if (val !== null && val !== '') {
                data[$(this).attr('id')] = $(this).val()
            }
        });
    data['password'] = encode(data['password']);
    return data;
};

export function showError(error) {
    $('#error').text(error);
}

export const isValidFormData = (form) => {
    const invalidFields = getAllInputs(form)
        .filter(function () {
            const val = $(this).val();
            const required = $(this).attr('required');
            return (required !== null && required !== undefined && required !== '')
                && (val === null || val === '');
        });
    if (invalidFields.length > 0) {
        showInvalidFields(invalidFields);
        return false;
    }
    return true;
};


function showInvalidFields(invalidFields) {
    invalidFields.each(function () {
        $(this).css('border', '2px solid red');
    });
    showError();
}

const encode = (str) => {
    return btoa(unescape(encodeURIComponent(str)));
};

const getAllInputs = (form) => {
    return form.find('input');
};