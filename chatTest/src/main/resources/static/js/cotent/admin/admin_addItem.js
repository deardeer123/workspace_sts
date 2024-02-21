

function previewImg(selectTag) {
    if (selectTag.files && selectTag.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            document.querySelector('#itemImg2').src = e.target.result;
        };
        reader.readAsDataURL(selectTag.files[0]);
    } else {
        document.querySelector('#itemImg2').src = "/upload/food_ham_egg.jpg";
    }
}