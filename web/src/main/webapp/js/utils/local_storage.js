export const getUser = () => {
    const token = localStorage.getItem('user');
    if (token !== null && token !== '') {
        return JSON.parse(atob(token));
    }
};