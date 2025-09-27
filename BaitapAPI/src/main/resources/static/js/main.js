// main.js

// Hàm để thực hiện các truy vấn GraphQL
function fetchGraphQL(query, variables) {
    return $.ajax({
        url: '/graphql',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ query, variables }),
        success: function(response) {
            console.log('Response:', response);
            if (response.errors) {
                console.error(response.errors);
                alert('Lỗi: ' + JSON.stringify(response.errors));
            }
            return response.data;
        }
    });
}

// Hàm để tải sản phẩm sắp xếp theo giá khi trang được tải
$(document).ready(function() {
    const query = `
        query {
            allProductsSortedByPrice {
                id
                title
                price
            }
        }
    `;
    fetchGraphQL(query).done(function(data) {
        $('#sortedProducts').empty();
        if (data && data.allProductsSortedByPrice) {
            data.allProductsSortedByPrice.forEach(product => {
                $('#sortedProducts').append(`<li>ID: ${product.id} - ${product.title} - $${product.price}</li>`);
            });
        } else {
            $('#sortedProducts').append('<li>Không có sản phẩm nào.</li>');
        }
    });
});

// Hàm để lấy sản phẩm theo Category ID
function fetchProductsByCategory() {
    const categoryId = $('#categoryId').val();
    const query = `
        query($categoryId: ID!) {
            productsByCategory(categoryId: $categoryId) {
                id
                title
                price
            }
        }
    `;
    fetchGraphQL(query, { categoryId }).done(function(data) {
        $('#categoryProducts').empty();
        if (data && data.productsByCategory) {
            data.productsByCategory.forEach(product => {
                $('#categoryProducts').append(`<li>ID: ${product.id} - ${product.title} - $${product.price}</li>`);
            });
        } else {
            $('#categoryProducts').append('<li>Không có sản phẩm nào trong danh mục này.</li>');
        }
    });
}

// CRUD User
function createUser() {
    const query = `
        mutation($fullname: String!, $email: String!, $password: String!, $phone: String) {
            createUser(fullname: $fullname, email: $email, password: $password, phone: $phone) {
                id
                fullname
            }
        }
    `;
    const variables = {
        fullname: $('#userFullname').val(),
        email: $('#userEmail').val(),
        password: $('#userPassword').val(),
        phone: $('#userPhone').val()
    };
    fetchGraphQL(query, variables).done(function(data) {
        alert('Tạo User thành công với ID: ' + data.createUser.id);
        $('#userFullname').val('');
        $('#userEmail').val('');
        $('#userPassword').val('');
        $('#userPhone').val('');
    });
}

// Các hàm khác cho cập nhật, xóa người dùng, danh mục, và sản phẩm cũng tương tự như vậy.

// Xuất các hàm để sử dụng trong HTML
window.fetchProductsByCategory = fetchProductsByCategory;
window.createUser = createUser;
// Xuất các hàm khác nếu cần thiết...