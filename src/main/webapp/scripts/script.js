const API_URL = 'http://localhost:8080/api';
    const CODE_TO_EMOJI = {
    'en': 'england.png',
    'pl': 'poland.png',
    'de': 'german.png'
    };

    fetch(`${API_URL}/langs`)
    .then(response => response.json())
    .then((languages) => {
    const checkboxes = languages.map(l => `
 
    <label class="pure-radio" style="margin: 10px;">
      <input type="radio" name="lang" value="${l.id}">
      <img src="../images/${CODE_TO_EMOJI[l.codeLanguage]}"</img>
    </label>

    `).join('');
    document.getElementById('langs').innerHTML = checkboxes;
    });
    const div = document.getElementById('welcome');
    const form = document.getElementById('welcomeForm');

    document.getElementById('btn').addEventListener('click', (event) => {
    event.preventDefault();
    const formObj = {
    name: form.elements.name.value,
    lang: form.elements.lang.value
    };
    fetch (`${API_URL}?${new URLSearchParams(formObj)}`)
      .then(response => response.text())
      .then((text) => {
       div.innerHTML = `
       <div class="row">
       <h1>${text}</h1>
       </div>
       `;
       });
});