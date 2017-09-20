(function () {
    var storage = localStorage;
    return {
        getItem: function (key) {
            if (typeof key === 'string') {
                return this.storage.getItem(key);
            } else {
                return 'Not able to fetch data from storage';
            }
        },
        setItem: function (key, value) {
            if (typeof key === 'string' && typeof value === 'string') {
                return this.storage.setItem(key, value);
            } else {
                return 'Not able to set data into storage';
            }
        },
        removeItem: function (key) {
            if (typeof key === 'string') {
                return this.storage.removeItem(key);
            } else {
                return 'Not able to remove data from storage';
            }
        },
        clear: function () {
            this.storage.clear();
        }
    }
})();