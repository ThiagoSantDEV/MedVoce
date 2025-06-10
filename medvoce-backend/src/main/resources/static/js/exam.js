document.addEventListener('DOMContentLoaded', function() {
    // Elementos do DOM
    const searchInput = document.getElementById('searchInput');
    const examsTable = document.getElementById('examsTable');
    const deleteButtons = document.querySelectorAll('.action-btn.delete');
    const confirmModal = document.getElementById('confirmModal');
    const cancelBtn = document.getElementById('cancelBtn');
    const examName = document.getElementById('examName');
    const deleteForm = document.getElementById('deleteForm');
    const modalMessage = document.getElementById('modalMessage');

    searchInput.addEventListener('input', function() {
        const searchTerm = this.value.toLowerCase();
        const rows = examsTable.querySelectorAll('tbody tr');

        rows.forEach(row => {
            const cells = row.querySelectorAll('td');
            let matches = false;

            for (let i = 0; i < cells.length - 1; i++) {
                if (cells[i].textContent.toLowerCase().includes(searchTerm)) {
                    matches = true;
                    break;
                }
            }

            row.style.display = matches ? '' : 'none';
        });
    });

    deleteButtons.forEach(button => {
        button.addEventListener('click', function() {
            const examId = this.getAttribute('data-id');
            const examTitle = this.getAttribute('data-name');

            examName.textContent = examTitle;
            deleteForm.action = `/exam/delete/${examId}`;
            confirmModal.style.display = 'flex';
        });
    });

    cancelBtn.addEventListener('click', function() {
        confirmModal.style.display = 'none';
    });

    window.addEventListener('click', function(event) {
        if (event.target === confirmModal) {
            confirmModal.style.display = 'none';
        }
    });

    function updateShowingCount() {
        const visibleRows = examsTable.querySelectorAll('tbody tr:not([style*="display: none"])');
        const totalRows = examsTable.querySelectorAll('tbody tr').length;

        if (visibleRows.length > 0) {
            const start = 1;
            const end = visibleRows.length;
            document.getElementById('showingCount').textContent = `${start}-${end}`;
        } else {
            document.getElementById('showingCount').textContent = '0';
        }
    }

    updateShowingCount();
    searchInput.addEventListener('input', updateShowingCount);
});