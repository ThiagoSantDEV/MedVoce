document.addEventListener('DOMContentLoaded', function() {
    // Validação do formulário
    const form = document.getElementById('agendaForm');
    const doctorSelect = document.getElementById('doctor');
    const dateInput = document.getElementById('date');

    // Configuração inicial da data (próximas 2 horas)
    const now = new Date();
    now.setHours(now.getHours() + 2);
    now.setMinutes(0);
    now.setSeconds(0);

    // Formata para o input datetime-local (YYYY-MM-DDTHH:MM)
    const formattedDate = now.toISOString().slice(0, 16);
    dateInput.value = formattedDate;
    dateInput.min = formattedDate;

    // Validação ao enviar o formulário
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

    // Funções auxiliares para mostrar/limpar erros
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

    // Validação em tempo real
    doctorSelect.addEventListener('change', function() {
        if (this.value) {
            clearError(this);
        }
    });

    dateInput.addEventListener('change', function() {
        if (this.value) {
            const selectedDate = new Date(this.value);
            const now = new Date();

            if (selectedDate < now) {
                showError(this, 'A data não pode ser no passado');
            } else {
                clearError(this);
            }
        }
    });
});