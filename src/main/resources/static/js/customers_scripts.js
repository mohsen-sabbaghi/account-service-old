$(document).ready(function () {
    $(function () {
        $('[data-bs-toggle="tooltip"]').tooltip()
    })

    let global_customer_id;
    let global_name;
    let global_surname;

    var createCustomerModal = document.getElementById('createCustomerModal')
    createCustomerModal.addEventListener('show.bs.modal', function (event) {

    })

    var createAccountModal = document.getElementById('createAccountModal')
    createAccountModal.addEventListener('show.bs.modal', function (event) {
        var button = event.relatedTarget

        global_customer_id = button.getAttribute('data-bs-customerId');
        global_name = button.getAttribute('data-bs-customerName')
        global_surname = button.getAttribute('data-bs-customerSurName')
        var modalTitle = createAccountModal.querySelector('.modal-title')
        modalTitle.textContent = 'Add new Account to ' + global_name + ' ' + global_surname
    })

    $('#addAccountBtn').on('click', function (event, param1, param2) {
        createAccountForExistingCustomer(global_customer_id, $("#initialCredit").val())
    })

});

function createAccountForExistingCustomer(id, initialCredit) {
    $.ajax({
        type: "POST",
        beforeSend: function (request) {
            request.setRequestHeader("Initial-Credit", initialCredit);
            request.setRequestHeader("Track-Id", Math.floor((Math.random() * 10000) + 1));
        },
        url: "/v1/customers/" + id + "/accounts",
        // data: "json=" + escape(JSON.stringify(createRequestObject)),
        processData: false,
        success: function (msg) {
            console.log(msg)
            // $("#results").append("The result =" + StringifyPretty(msg));
            // location.reload(true);
            $('#tbl_accounts').DataTable().ajax.reload();
        },
        error: function (msg) {
            console.log("error",msg)
        },
        afterSend:function ( ) {
            console.log("afterSend")
        }
    });
}