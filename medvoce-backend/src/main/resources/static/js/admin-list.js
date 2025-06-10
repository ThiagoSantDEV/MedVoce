
    function showDeleteModal(id, name) {
    document.getElementById('deleteModalText').innerHTML =
        `Tem certeza que deseja excluir o administrador <strong>${name}</strong>? Esta ação não pode ser desfeita.`;

    document.getElementById('deleteForm').action = `/admin/delete/${id}`;
    document.getElementById('deleteModal').style.display = 'flex';
}

    function hideDeleteModal() {
    document.getElementById('deleteModal').style.display = 'none';
}


    window.onclick = function(event) {
    const modal = document.getElementById('deleteModal');
    if (event.target === modal) {
    hideDeleteModal();
}
};


    document.getElementById('searchInput').addEventListener('keyup', function() {
    const input = this.value.toLowerCase();
    const rows = document.querySelectorAll('#adminsTable tbody tr');

    rows.forEach(row => {
    const name = row.cells[1].textContent.toLowerCase();
    const email = row.cells[2].textContent.toLowerCase();

    if (name.includes(input) || email.includes(input)) {
    row.style.display = '';
} else {
    row.style.display = 'none';
}
});
});