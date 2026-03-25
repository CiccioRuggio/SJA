const questions = [
    {
        q: "What does HTML stand for?",
        opts: ["HyperText Markup Language", "High Transfer Markup Logic", "HyperText Modular Link", "Hyper Transfer Machine Language"],
        a: 0,
        exp: "<strong>HTML</strong> = HyperText Markup Language. It's the skeleton of every web page — it defines structure and content using tags like &lt;h1&gt;, &lt;p&gt;, &lt;div&gt; and so on."
    },
    {
        q: "Which language is primarily responsible for styling and layout on a web page?",
        opts: ["JavaScript", "Python", "CSS", "SQL"],
        a: 2,
        exp: "<strong>CSS</strong> (Cascading Style Sheets) controls colours, fonts, spacing, and layout. Without it, every web page would look like a plain text document."
    },
    {
        q: "Which of these is an AI coding assistant that suggests code directly inside your editor?",
        opts: ["Figma", "GitHub Copilot", "Postman", "Webpack"],
        a: 1,
        exp: "<strong>GitHub Copilot</strong> (powered by OpenAI Codex) autocompletes code, writes functions from comments, and dramatically speeds up development. It's used by millions of devs worldwide."
    },
    {
        q: "What does API stand for in web development?",
        opts: ["Automatic Programming Interface", "Application Protocol Integrator", "Application Programming Interface", "Advanced Process Integration"],
        a: 2,
        exp: "<strong>API</strong> = Application Programming Interface. It's a set of rules that allows different software to communicate. For example, a weather app calls a weather API to get today's forecast."
    },
    {
        q: "Which JavaScript framework was created by Facebook (Meta)?",
        opts: ["Vue.js", "Angular", "Svelte", "React"],
        a: 3,
        exp: "<strong>React</strong> was created by Meta (Facebook) in 2013. It introduced a component-based model and a virtual DOM that made building dynamic UIs much more manageable."
    },
    {
        q: "What does LLM stand for in the context of AI?",
        opts: ["Low-Level Machine", "Large Language Model", "Layered Learning Module", "Linear Logic Map"],
        a: 1,
        exp: "<strong>LLM</strong> = Large Language Model. These are AI models trained on massive amounts of text data. GPT-4, Claude, and Gemini are all LLMs — they power most modern AI chatbots."
    },
    {
        q: "What is the main purpose of a CSS media query?",
        opts: ["To embed audio files", "To query a database", "To apply styles based on screen size or device", "To import Google Fonts"],
        a: 2,
        exp: "<strong>Media queries</strong> make responsive design possible. With @media (max-width: 768px) you can apply different CSS rules for mobile screens, tablets, and desktops."
    },
    {
        q: "Which AI technique is commonly used to generate realistic images from a text description?",
        opts: ["Decision Trees", "Diffusion Models", "Linear Regression", "K-Means Clustering"],
        a: 1,
        exp: "<strong>Diffusion Models</strong> (used by Stable Diffusion, DALL-E, Midjourney) work by gradually removing noise from random pixels, guided by the text prompt, until a coherent image emerges."
    },
    {
        q: "In web development, what does 'responsive design' mean?",
        opts: [
            "The website replies to user messages",
            "The website loads faster on a server",
            "The website layout adapts to different screen sizes",
            "The website uses AI to auto-generate content"
        ],
        a: 2,
        exp: "<strong>Responsive design</strong> ensures your site looks and works well on any screen — phones, tablets, laptops. It's achieved using flexible grids, fluid images, and CSS media queries."
    },
    {
        q: "Which of the following best describes how AI is already changing web development?",
        opts: [
            "AI has completely replaced human developers",
            "AI only writes backend code, not frontend",
            "AI tools help developers write, debug, and review code faster",
            "AI is not yet used in web development"
        ],
        a: 2,
        exp: "<strong>AI augments developers</strong> — it doesn't replace them. Tools like Copilot, ChatGPT, and Claude help write boilerplate, debug errors, generate test cases, and explain complex code. The developer is still in charge."
    }
];

let current = 0, score = 0;

function startQuiz() {
    document.getElementById('intro').classList.add('hidden');
    document.getElementById('quiz').classList.remove('hidden');
    renderQuestion();
}

function renderQuestion() {
    const q = questions[current];
    const letters = ['A', 'B', 'C', 'D'];

    document.getElementById('q-num').textContent = `QUESTION ${String(current + 1).padStart(2, '0')}`;
    document.getElementById('q-text').textContent = q.q;
    document.getElementById('prog-label').textContent = `Question ${current + 1} of ${questions.length}`;
    document.getElementById('prog-score').textContent = `Score: ${score}`;
    document.getElementById('prog-fill').style.width = `${(current / questions.length) * 100}%`;
    document.getElementById('explanation').classList.remove('show');
    document.getElementById('explanation').innerHTML = '';
    document.getElementById('next-btn').classList.remove('show');

    // Re-animate card
    const card = document.getElementById('q-card');
    card.style.animation = 'none';
    card.offsetHeight;
    card.style.animation = '';

    const opts = document.getElementById('options');
    opts.innerHTML = '';
    q.opts.forEach((o, i) => {
        const btn = document.createElement('button');
        btn.className = 'opt';
        btn.innerHTML = `<span class="letter">${letters[i]}</span>${o}`;
        btn.onclick = () => answer(i);
        opts.appendChild(btn);
    });
}

function answer(chosen) {
    const q = questions[current];
    const btns = document.querySelectorAll('.opt');

    btns.forEach(b => b.disabled = true);
    btns[q.a].classList.add('correct');
    if (chosen !== q.a) btns[chosen].classList.add('wrong');
    else score++;

    document.getElementById('prog-score').textContent = `Score: ${score}`;

    const exp = document.getElementById('explanation');
    exp.innerHTML = q.exp;
    exp.classList.add('show');
    document.getElementById('next-btn').classList.add('show');
}

function nextQuestion() {
    current++;
    if (current >= questions.length) showResults();
    else renderQuestion();
}

function showResults() {
    document.getElementById('quiz').classList.add('hidden');
    document.getElementById('results').classList.remove('hidden');

    const pct = Math.round((score / questions.length) * 100);
    document.getElementById('final-score').textContent = score;
    document.getElementById('stat-correct').textContent = score;
    document.getElementById('stat-wrong').textContent = questions.length - score;
    document.getElementById('stat-pct').textContent = pct + '%';

    // Animate ring
    const circumference = 427;
    const offset = circumference - (pct / 100) * circumference;
    setTimeout(() => {
        document.getElementById('ring-fill').style.strokeDashoffset = offset;
    }, 100);

    // Feedback
    let title, msg;
    if (score >= 9) { title = "🏆 Legendary!"; msg = "Outstanding performance! You're clearly ahead of the curve in both web dev and AI. Future full-stack engineer? Definitely."; }
    else if (score >= 7) { title = "🚀 Great Work!"; msg = "You've got a solid grasp of web development and AI. A few gaps here and there, but you're well on your way."; }
    else if (score >= 5) { title = "💡 Not Bad!"; msg = "You know the basics, but there's plenty of room to explore further. Dive deeper into AI tools — it's worth it!"; }
    else if (score >= 3) { title = "📚 Keep Learning"; msg = "This stuff takes time. The good news? You now have 10 explanations to revisit. Web dev + AI is the most exciting combo out there."; }
    else { title = "🌱 Just Starting"; msg = "Everyone starts somewhere! Read through the explanations, come back, and crush it on the next round."; }

    document.getElementById('result-title').textContent = title;
    document.getElementById('result-msg').textContent = msg;
}

function restartQuiz() {
    current = 0; score = 0;
    document.getElementById('results').classList.add('hidden');
    document.getElementById('quiz').classList.remove('hidden');
    renderQuestion();
}