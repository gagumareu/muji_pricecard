
const saveArticleBtn = document.getElementById('create-btn');

if (saveArticleBtn){
    saveArticleBtn.addEventListener('click', (event) => {

        let str = '';

        const form = document.querySelector('form');

        const uploadli = document.querySelector('.uploadResultDiv').getElementsByTagName('li');

        const fileHidden = document.querySelector('.fileHidden');

        for (let i = 0; i < uploadli.length; i++){

            const fileName = uploadli[i].getAttribute('data-s3url')
            console.log(fileName)

            str += `<input type="hidden" name="fileNames" value="${fileName}">`

            console.log(uploadli[i]);
        }
        fileHidden.innerHTML = str;
        form.submit();
    });
}

const deleteArticleBtn = document.querySelector('.article-delete-btn');

if (deleteArticleBtn){
    deleteArticleBtn.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        console.log(id);
        fetch(`/api/article/${id}`, {
            method: "DELETE"
        })
            .then(() => {
                alert('삭제가 완료되었습니다.');
                location.replace('/articles');
            });
    });
}

const updateArticleBtn = document.getElementById('update-btn');

if (updateArticleBtn){
    updateArticleBtn.addEventListener('click', event => {

        let param = new URLSearchParams(location.search);
        let id = param.get('id');
        let fileList = []

        const uploadLi = document.querySelector('.uploadResultUL').getElementsByTagName('li');

        for (let i = 0; i < uploadLi.length; i++){

            let s3Url = uploadLi[i].getAttribute('data-s3url');

            console.log(s3Url)

            fileList.push(s3Url)
        }

        fetch(`/api/article/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.querySelector('.article-title-value').value,
                content: document.getElementById('summernote').value,
                writer: document.querySelector('.article-writer-value').value,
                category: document.querySelector('.article-category-value').value,
                fileNames: fileList
            })
        })
        .then(() => {
            alert("수정 완료");
            // location.replace(`/article/${id}`)
        });
    });
}
