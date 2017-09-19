/*
 ** CREATED BY       : GAURI SHANKER (B0094509)
 ** CHANGED BY 		  : GAURI SHANKER (B0094509)
 ** LAST CHANGE DATE : 17/01/2017
 ** CHANGE LOG 		  : CREATED 
 */
/************************ GULP PLUGINS **************************/
var gulp = require('gulp');
var jsmin = require('gulp-jsmin');
var uglify = require('gulp-uglify');
var rename = require('gulp-rename');
var obfuscate = require('gulp-obfuscate');
var concat = require('gulp-concat');
var ngAnnotate = require('gulp-ng-annotate');
var cleanCSS = require('gulp-clean-css');
var imagemin = require('gulp-imagemin');
var htmlmin = require('gulp-htmlmin');
var scp = require('gulp-scp2');
var replace = require('gulp-replace');
var ignore = require('gulp-ignore');
var version = require('gulp-version-number');
var gulpUtil = require('gulp-util');
/*TASKS*/
// MINIFY + UGLIFY + RENAME JS
gulp.task('minifyJs', function () {
    gulp.src('app.js')
        .pipe(jsmin())
        .pipe(rename({
            suffix: '.min'
        }))
        .pipe(gulp.dest('build/scripts'));
});
// UGLIFY JS
gulp.task('uglifyJs', function () {
    gulp.src('app.js')
        .pipe(jsmin())
        .pipe(ignore.exclude(["**/*.map"]))
        .pipe(uglify().on('error', gulpUtil.log))
        .pipe(rename({
            suffix: '.min'
        }))
        .pipe(gulp.dest('build/scripts'));
});
// OBFUSCATE JS
gulp.task('obfuscateJs', function () {
    gulp.src('app.js')
        .pipe(jsmin())
        .pipe(uglify())
        .pipe(obfuscate())
        .pipe(rename({
            suffix: '.min'
        }))
        .pipe(gulp.dest('build/scripts'));
});
// CONCAT AND MINIFY JS
gulp.task('scripts', function () {
    gulp.src([
            'app/libs/loader.js',
            'app/libs/jquery.min.js',
            'app/libs/jquery-ui.min.js',
            'app/libs/bootstrap.min.js',
            'app/libs/common.js',
            'app/libs/angular.js',
            'app/libs/angular-route.js',
            'app/libs/angular-cookies.js',
            'app/libs/angular-messages.js',
            'app/libs/ng-file-upload.min.js',
            'app/libs/angularjs-crypto.js',
            'app/common/appConstants.js',
            'app/libs/smart-table.min.js',
            'app/libs/bootstrap-select.min.js',
            'app/libs/moment.js',
            'app/libs/bootstrap-datetimepicker.min.js',
            'app/libs/ng-loader.js',
            'app/app.js',
            'app/common/services/encryption.service.js',
            'app/modules/login/controllers/login.js',
            'app/modules/retailer/controllers/offline.js',
            'app/modules/broadcast/controllers/broadcast.controller.js',
            'app/modules/userManagement/controllers/userController.js',
            'app/modules/broadcast/services/broadcast.services.js',
            'app/modules/login/services/login.services.js',
            'app/modules/retailer/services/uploader.services.js',
            'app/modules/retailer/services/fileLoader.services.js',
            'app/modules/userManagement/services/userManagement.services.js',
        ])
        .pipe(concat('script.js'))
        .pipe(jsmin())
        //.pipe(ngAnnotate())
        //.pipe(ignore.exclude([ "**/*.map" ]))
        //.pipe(uglify({mangle: true}).on('error', gulpUtil.log))
        .pipe(rename({
            suffix: '.min'
        }))
        .pipe(gulp.dest('build/scripts'));
});
// CONCAT AND MINIFY CSS
gulp.task('styles', function () {
    gulp.src(
            [
                'app/css/loader.css',
                'app/css/jquery-ui.min.css',
                'app/css/angular-block-ui.css',
                'app/css/bootstrap-datetimepicker.min.css',
                'app/css/bootstrap-select.css',
                'app/css/bootstrap-theme.css',
                'app/css/bootstrap-bootstrap.css',
                'app/css/signin.css',
                'app/css/common.css'
            ])
        .pipe(concat('style.css'))
        .pipe(cleanCSS({
            compatibility: 'ie9'
        }))
        .pipe(rename({
            suffix: '.min'
        }))
        .pipe(gulp.dest('build/styles'));
});
// MINIFY IMAGES
gulp.task('minifyImages', function () {
    gulp.src(['build/images/*.png', 'build/images/icons/*.png'])
        .pipe(imagemin())
        .pipe(gulp.dest('build/imagesmin'));
});
// COPY FONTS
gulp.task('copyFonts', function () {
    gulp.src('app/fonts/*.*')
        .pipe(gulp.dest('build/fonts'));
});
// COPY IMAGES
gulp.task('copyImages', function () {
    gulp.src(['app/img/*.*'])
        .pipe(imagemin())
        .pipe(gulp.dest('build/images'));
});
// COPY VIEWS
gulp.task('copy-html', function () {
    gulp.src(['modules/broadcast/views/*.*', 'modules/login/views/*.*', 'modules/retailer/views/*.*', 'modules/userManagement/views/*.*'])
        .pipe(gulp.dest('build/views'));
});
// COPY INDEX
gulp.task('copy-index', function () {
    gulp.src(['index_deliverable.html'])
        .pipe(rename('index.html'))
        .pipe(version({
            /**
             * Global version value
             * default: %MDS%
             */
            'value': '%MDS%',
            /**
             * MODE: REPLACE
             * eg:
             *    'keyword'
             *    /regexp/ig
             *    ['keyword']
             *    [/regexp/ig, '%MD5%']]
             */
            'replaces': [
                /**
                 * {String|Regexp} Replace Keyword/Rules to global value (config.value)
                 */
                '#{VERSION_REPlACE}#',
                /**
                 * {Array}
                 * Replace keyword to custom value
                 * if just have keyword, the value will use the global value (config.value).
                 */
                [/#{VERSION_REPlACE}#/g, '%MDS%']
            ],
            /**
             * MODE: APPEND
             * Can coexist and replace, after execution to replace
             */
            'append': {
                /**
                 * Parameter
                 */
                'key': '_v',
                /**
                 * Whether to overwrite the existing parameters
                 * default: 0 (don't overwrite)
                 * If the parameter already exists, as a "custom", covering not executed.
                 * If you need to cover, please set to 1
                 */
                'cover': 0,
                /**
                 * Appended to the position (specify type)
                 * {String|Array|Object}
                 * If you set to 'all', will apply to all type, rules will use the global setting.
                 * If an array or object, will use your custom rules.
                 * others will passing.
                 * 
                 * eg:
                 *     'js'
                 *     ['js']
                 *     {type:'js'}
                 *     ['css', '%DATE%']
                 */
                'to': [
                    /**
                     * {String} Specify type, the value is the global value
                     */
                    'css',
                    /**
                     * {Array}
                     * Specify type, keyword and cover rules will use the global 
                     * setting, If you need more details, please use the object 
                     * configure.
                     *
                     * argument 0 necessary, otherwise passing.
                     * argument 1 optional, the value will use the global value
                     */
                    ['image', '%MDS%'],
                    /**
                     * {Object}
                     * Use detailed custom rules to replace, missing items will 
                     * be taken in setting the global completion
                     * type is necessary, otherwise passing.
                     */
                    {
                        'type': 'js',
                        'key': '_v',
                        'value': '%MDS%',
                        'cover': 1
                    }
                ]
            },
            /**
             * Output to config file
             */
            'output': {
                'file': 'version.json'
            }
        }))
        .pipe(gulp.dest('build'));
});
// DEPLOY TO SERVER
gulp.task('deployToServer', function () {
    gulp.src('build/**')
        .pipe(scp({
            host: '10.5.204.43',
            username: 'devops',
            password: 'aadhar@321',
            dest: '/var/www/html/eCAF/opusApp'
        }))
        .on('error', function (err) {
            console.log(err);
        });
});
// DEPLOY TO STAGING SERVER
gulp.task('deployToStaging', function () {
    gulp.src('build/**')
        .pipe(scp({
            host: '10.5.200.97',
            port: '31724',
            username: 'root',
            password: 'airtel',
            dest: '/etc/nginx/html/eCAF/opusApp'
        }))
        .on('error', function (err) {
            console.log(err);
        });
});
// DEPLOY TO SERVER
gulp.task('release', function () {
    gulp.src('build/**')
        .pipe(scp({
            host: '10.5.198.250',
            username: 'eca_user',
            password: 'eca_user',
            dest: '/opus/prodbuild/opus-ui'
        }))
        .on('error', function (err) {
            console.log(err);
        });
});
// AUTOMATE TASKS
gulp.task('deploy', ['default', 'deployToServer']);
gulp.task('default', ['copyFonts', 'copyImages', 'copy-html', 'copy-index', 'scripts', 'styles']);