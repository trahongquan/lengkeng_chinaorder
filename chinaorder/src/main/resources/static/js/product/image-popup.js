var fileTempl = document.getElementById("file-template"),
    imageTempl = document.getElementById("image-template"),
    empty = document.getElementById("empty");

function addFile(target, file) {
    var isImage = file.type.match("image.*"),
        objectURL = URL.createObjectURL(file);

    var clone = isImage
        ? imageTempl.content.cloneNode(true)
        : fileTempl.content.cloneNode(true);

    clone.querySelector("h7").textContent = file.name;
    clone.querySelector("li").id = objectURL;
    clone.querySelector(".delete").dataset.target = objectURL;
    clone.querySelector(".size").textContent = file.size > 1024
        ? file.size > 1048576
            ? Math.round(file.size / 1048576) + "mb"
            : Math.round(file.size / 1024) + "kb"
        : file.size + "b";

    if (isImage) {
        Object.assign(clone.querySelector("img"), {
            src: objectURL,
            alt: file.name
        });
    }

    empty.classList.add("hidden");
    target.prepend(clone);
}

var gallery = document.getElementById("gallery"),
    overlay = document.getElementById("overlay");

var hidden = document.getElementById("hidden-input");
document.getElementById("button").onclick = () => hidden.click();
hidden.onchange = (e) => {
    for (var file of e.target.files) {
        addFile(gallery, file);
    }
};

var counter = 0;

function dropHandler(ev) {
    ev.preventDefault();
    for (var file of ev.dataTransfer.files) {
        addFile(gallery, file);
        overlay.classList.remove("draggedover");
        counter = 0;
    }
}

function dragEnterHandler(e) {
    e.preventDefault();
    ++counter && overlay.classList.add("draggedover");
}

function dragLeaveHandler(e) {
    1 > --counter && overlay.classList.remove("draggedover");
}

function dragOverHandler(e) {
    e.preventDefault();
}

gallery.onclick = ({ target }) => {
    if (target.classList.contains("delete")) {
        var ou = target.dataset.target;
        document.getElementById(ou).remove(ou);
        gallery.children.length === 1 && empty.classList.remove("hidden");
    }
};

document.getElementById("cancel").onclick = () => {
    while (gallery.children.length > 0) {
        gallery.lastChild.remove();
    }
    empty.classList.remove("hidden");
    gallery.append(empty);
};
