function addressSearch() {
    new daum.Postcode({
        oncomplete: function(data) {
            document.getElementById("addr_number").value =data.zonecode;
            document.getElementById("addr1").value = data.address;
        }
    }).open();
}