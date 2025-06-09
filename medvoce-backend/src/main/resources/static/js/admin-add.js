    function checkPasswordStrength(password) {
    const meter = document.getElementById('password-strength-meter');
    let strength = 0;

    // Verifica o comprimento
    if (password.length >= 8) strength += 1;
    if (password.length >= 12) strength += 1;

    // Verifica caracteres diversos
    if (/[A-Z]/.test(password)) strength += 1;
    if (/[0-9]/.test(password)) strength += 1;
    if (/[^A-Za-z0-9]/.test(password)) strength += 1;

    // Atualiza a barra de força
    const width = (strength / 5) * 100;
    meter.style.width = width + '%';

    // Atualiza a cor
    if (strength <= 2) {
    meter.style.backgroundColor = '#e63757'; // Fraco
} else if (strength <= 4) {
    meter.style.backgroundColor = '#f6c343'; // Médio
} else {
    meter.style.backgroundColor = '#00d97e'; // Forte
}
}
