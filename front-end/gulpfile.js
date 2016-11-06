var gulp = require('gulp'), // Подключаем Gulp
    sass = require('gulp-sass'), // Подключаем sass/scss
    cssmin = require('gulp-minify-css'), // Подключаем минимизатор для css
    autoprefixer = require('gulp-autoprefixer'), // Подключаем автопрефиксер
    sourcemaps = require('gulp-sourcemaps'), // Подключим soucemaps
    rigger = require('gulp-rigger'), // для склеивания файлов
    uglify = require('gulp-uglify'), // для сжатия js
    browserSync = require('browser-sync'), // Подключаем Browser Sync;
    rimraf = require('rimraf'), // rm rf для ноды
    babel = require('gulp-babel'), // для использования es2015 в старых браузерах
    
    // переменные путей
    path = {
      // источник
      src: { //Пути откуда брать исходники
        html: 'app/*.html', //Синтаксис src/*.html говорит gulp что мы хотим взять все файлы с расширением .html
        js: 'app/js/main.js', //В стилях и скриптах нам понадобятся только main файлы
        style: 'app/scss/main.scss',
        img: 'app/img/**/*.*', //Синтаксис img/**/*.* означает - взять все файлы всех расширений из папки и из вложенных каталогов
        fonts: 'app/fonts/**/*.*'
    },
      // куда складывать готовые после сборки файлы
      dist: { 
        html: 'dist/',
        js: 'dist/js/',
        css: 'dist/css/',
        img: 'dist/img/',
        fonts: 'dist/fonts/'
      },
      // за какими файлами будем наблюдать
      watch: {
        html: 'app/**/*.html',
        js: 'app/js/**/*.js',
        scss: 'app/scss/**/*.scss',
        img: 'app/img/**/*.*',
        fonts: 'app/fonts/**/*.*'
      },
      // что будем подчищать
      clean: './dist'
    };

// BROWSER SYNC
gulp.task('browser-sync', function() { // Создаем таск browser-sync
    browserSync({ // Выполняем browser Sync
        server: { // Определяем параметры сервера
            baseDir: './dist' // Директория для сервера - dist
        },
        notify: false // Отключаем уведомления
    });
});


// HTML
gulp.task('html', function() { // Создаем таск для HTML
  gulp.src(path.src.html) // Указываем ресурс
  .pipe(gulp.dest(path.dist.html)) //Выплюнем их в папку dist
  .pipe(browserSync.reload({stream:true})); // Выполняем обновление в браузере
});

// SCSS
gulp.task('scss', function() { // Создаем таск "sass"
    gulp.src(path.src.style) // Берем источник
        .pipe(sourcemaps.init()) // Инициализируем sourcemaps
        .pipe(sass().on('error', sass.logError))// Преобразуем Scss в CSS посредством gulp-sass
        .pipe(autoprefixer({ // Добавляем префиксы
            browsers: ['last 10 versions'], 
            cascade: false
        }))
        .pipe(cssmin()) // Минимизируем их
        .pipe(sourcemaps.write()) // Запишем sourcemaps
        .pipe(gulp.dest(path.dist.css)) // Выгружаем результат в папку dist/css
        .pipe(browserSync.reload({stream: true})) // Обновляем CSS на странице при изменении
});

// IMAGES
gulp.task('image', function () {
    gulp.src(path.src.img) //Выберем наши картинки
        .pipe(gulp.dest(path.dist.img)) //И бросим dist
        .pipe(browserSync.reload({stream: true}));
});

// FONTS
gulp.task('fonts', function() {
    gulp.src(path.src.fonts)
        .pipe(gulp.dest(path.dist.fonts))
        .pipe(browserSync.reload({stream: true}));
});

// JS
gulp.task('js', function () {
    gulp.src(path.src.js) //Найдем наш main файл
        .pipe(rigger()) //Прогоним через rigger
        .pipe(sourcemaps.init()) //Инициализируем sourcemap
        .pipe(babel({ presets: ['es2015'] })) // перепишем на старый js
        .pipe(uglify()) //Сожмем наш js
        .pipe(sourcemaps.write()) //Пропишем карты
        .pipe(gulp.dest(path.dist.js)) //Выплюнем готовый файл в build
        .pipe(browserSync.reload({stream: true})); //И перезагрузим сервер
});

// WATCHER
gulp.task('watch', function() {
    gulp.watch(path.watch.html, ['html']); // Наблюдение за HTML файлами
    gulp.watch(path.watch.scss, ['scss']); // Наблюдение за SCSS файлами
    gulp.watch(path.watch.js, ['js']); // Наблюдение за картинками
    gulp.watch(path.watch.fonts, ['fonts']); // Наблюдение шрифтами
    gulp.watch(path.watch.img, ['image']); // Наблюдение за картинками
    // Наблюдение за другими типами файлов
});

// CLEANER
gulp.task('clean', function (cb) {
    rimraf(path.clean, cb);
});

gulp.task('develop', ['html', 'scss', 'js', 'image', 'fonts', 'watch', 'browser-sync']); // Инициализация всех файлов, включения вотчера и автообновления в браузере