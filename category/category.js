$(document).ready(function() {

    CategoryLoad();

    $('#category-form').on('submit', function(event) {
        event.preventDefault();
        const id = $('#category-id').val();
        const name = $('#category-name').val();

        if (id) {
            updateCategory(id, name);
        } else {
            addCategory(name);
        }
    });
});


function CategoryLoad() {
    $.ajax({
        url: 'http://localhost:8080/api/categories',
        type: 'GET',
        success: function(categories) {
            let categoryBody = $('#category-body');
            categoryBody.empty();

            $.each(categories, function(i, category) {
                categoryBody.append(`
                    <tr>
                        <td>${category.id}</td>
                        <td>${category.name}</td>
                        <td>
                            <button onclick="editCategory(${category.id})">Edit</button>
                            <button onclick="deleteCategory(${category.id})">Delete</button>
                        </td>
                    </tr>
                `);
            });
        },
        error: function() {
            alert('Unable to load categories');
        }
    });
}


function addCategory(name) {
    const categoryData = { name: name };

    $.ajax({
        url: 'http://localhost:8080/api/categories',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(categoryData),
        success: function() {
            alert('Category added successfully');
            CategoryLoad();
            resetCategoryForm();
        },
        error: function() {
            alert('Error adding category');
        }
    });
}

function updateCategory(id, name) {
    const categoryData = { id: id, name: name };

    $.ajax({
        url: `http://localhost:8080/api/categories/${id}`,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(categoryData),
        success: function() {
            alert('Category updated successfully');
            CategoryLoad();
            resetCategoryForm();
        },
        error: function() {
            alert('Error updating category');
        }
    });
}


function deleteCategory(id) {
    $.ajax({
        url: `http://localhost:8080/api/categories/${id}`,
        type: 'DELETE',
        success: function() {
            alert('Category deleted successfully');
            CategoryLoad();
        },
        error: function() {
            alert('Error deleting category');
        }
    });
}


function editCategory(id) {
    $.ajax({
        url: `http://localhost:8080/api/categories/${id}`,
        type: 'GET',
        success: function(category) {
            $('#category-id').val(category.id);
            $('#category-name').val(category.name);
            $('#category-submit-button').text('Update Category');
        },
        error: function() {
            alert('Error loading category data');
        }
    });
}

function resetCategoryForm() {
    $('#category-id').val('');
    $('#category-name').val('');
    $('#category-submit-button').text('Add Category');
}
