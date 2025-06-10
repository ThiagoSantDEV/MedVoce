
    function togglePasswordVisibility() {
    const passwordField = document.getElementById('password');
    const toggleIcon = document.querySelector('.toggle-password i');

    if (passwordField.type === 'password') {
    passwordField.type = 'text';
    toggleIcon.classList.remove('fa-eye');
    toggleIcon.classList.add('fa-eye-slash');
} else {
    passwordField.type = 'password';
    toggleIcon.classList.remove('fa-eye-slash');
    toggleIcon.classList.add('fa-eye');
}
}

    function validatePassword(password) {
    const requirements = {
    length: password.length >= 8,
    uppercase: /[A-Z]/.test(password),
    number: /[0-9]/.test(password),
    special: /[^A-Za-z0-9]/.test(password)
};

    document.getElementById('req-length').className = requirements.length ? 'valid' : '';
    document.getElementById('req-uppercase').className = requirements.uppercase ? 'valid' : '';
    document.getElementById('req-number').className = requirements.number ? 'valid' : '';
    document.getElementById('req-special').className = requirements.special ? 'valid' : '';
}

    function confirmDelete() {
    document.getElementById('deleteModal').style.display = 'flex';
}

    window.onclick = function(event) {
    const modal = document.getElementById('deleteModal');
    if (event.target === modal) {
    modal.style.display = 'none';
}
};