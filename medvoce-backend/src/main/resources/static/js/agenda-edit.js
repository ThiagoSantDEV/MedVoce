document.addEventListener('DOMContentLoaded', function() {
    // Elementos do DOM
    const form = document.getElementById('agendaForm');
    const doctorSelect = document.getElementById('doctor');
    const dateInput = document.getElementById('date');
    const deleteBtn = document.getElementById('deleteBtn');
    const confirmModal = document.getElementById('confirmModal');
    const cancelDelete = document.getElementById('cancelDelete');

    // Validação do formulário
    form.addEventListener('submit', function(event) {
        let isValid = true;

        // Validação do médico
        if (!doctorSelect.value) {
            showError(doctorSelect, 'Selecione um médico');
            isValid = false;
        } else {
            clearError(doctorSelect);
        }

        // Validação da data
        if (!dateInput.value) {
            showError(dateInput, 'Selecione uma data e hora');
            isValid = false;
        } else {
            const selectedDate = new Date(dateInput.value);
            const now = new Date();

            if (selectedDate < now) {
                showError(dateInput, 'A data não pode ser no passado');
                isValid = false;
            } else {
                clearError(dateInput);
            }
        }

        if (!isValid) {
            event.preventDefault();
        }
    });

    // Modal de exclusão
    deleteBtn.addEventListener('click', function() {
        confirmModal.style.display = 'flex';
    });

    cancelDelete.addEventListener('click', function() {
        confirmModal.style.display = 'none';
    });

    // Fechar modal ao clicar fora
    window.addEventListener('click', function(event) {
        if (event.target === confirmModal) {
            confirmModal.style.display = 'none';
        }
    });

    // Funções auxiliares para validação
    function showError(input, message) {
        const formGroup = input.closest('.form-group');
        input.classList.add('invalid');

        let errorMessage = formGroup.querySelector('.error-message');
        if (!errorMessage) {
            errorMessage = document.createElement('span');
            errorMessage.className = 'error-message';
            formGroup.appendChild(errorMessage);
        }

        errorMessage.textContent = message;
    }

    function clearError(input) {
        const formGroup = input.closest('.form-group');
        input.classList.remove('invalid');

        const errorMessage = formGroup.querySelector('.error-message');
        if (errorMessage) {
            errorMessage.remove();
        }
    }
});