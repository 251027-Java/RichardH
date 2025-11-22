(() => {
    const elements = {
        input: document.getElementById('urlInput'),
        btn: document.getElementById('fetchBtn'),
        btnIcon: document.getElementById('btnIcon'),
        output: document.getElementById('jsonOutput'),
        status: document.getElementById('statusContainer'),
        copyBtn: document.getElementById('copyBtn'),
        quickLinks: document.querySelectorAll('.quick-link')
    };

    const uiState = {
        setLoading(isLoading) {
            elements.btn.disabled = isLoading;
            elements.input.disabled = isLoading;

            if (isLoading) {
                elements.btnIcon.innerHTML = '⟳'; // Simple loading character
                elements.btnIcon.classList.add('spin');
                elements.btn.textContent = ' Loading...';
                elements.btn.prepend(elements.btnIcon);
                this.updateStatus('info', 'Fetching data...');
            } else {
                elements.btnIcon.innerHTML = '➤';
                elements.btnIcon.classList.remove('spin');
                elements.btn.textContent = ' Fetch';
                elements.btn.prepend(elements.btnIcon);
            }
        },

        updateStatus(type, message) {
            elements.status.classList.remove('hidden');
            // Reset classes
            elements.status.className = 'status-box';
            // Add specific type class
            elements.status.classList.add(`status-${type}`);
            elements.status.textContent = message;
        },

        displayData(data) {
            elements.output.textContent = JSON.stringify(data, null, 2);
        }
    };

    const handleFetch = async () => {
        const url = elements.input.value.trim();
        if (!url) return;

        uiState.setLoading(true);
        elements.output.textContent = '// Fetching...';

        try {
            const response = await fetch(url);

            if (!response.ok) {
                throw new Error(`HTTP Error: ${response.status}`);
            }

            const data = await response.json();
            uiState.displayData(data);
            uiState.updateStatus('success', `Success: ${response.status} OK`);

        } catch (error) {
            let msg = error.message;
            if (msg.includes('Failed to fetch')) msg += ' (Likely CORS issue)';

            uiState.updateStatus('error', msg);
            elements.output.textContent = '// Request Failed';
        } finally {
            uiState.setLoading(false);
        }
    };

    // Event Listeners
    elements.btn.addEventListener('click', handleFetch);

    elements.quickLinks.forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            elements.input.value = e.target.dataset.url;
            handleFetch();
        });
    });

    elements.copyBtn.addEventListener('click', () => {
        if (elements.output.textContent.includes('//')) return;
        navigator.clipboard.writeText(elements.output.textContent);
        const original = elements.copyBtn.textContent;
        elements.copyBtn.textContent = 'Copied!';
        setTimeout(() => elements.copyBtn.textContent = original, 1500);
    });

})();