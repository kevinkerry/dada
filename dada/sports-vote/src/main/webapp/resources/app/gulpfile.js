var gulp = require('gulp');
var gutil = require('gulp-util');
var bower = require('bower');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var minifyCss = require('gulp-minify-css');
var rename = require('gulp-rename');
var sh = require('shelljs');
var plumber = require( 'gulp-plumber' );
var autoprefixer = require('gulp-autoprefixer');
var compass = require('gulp-compass');

var paths = {
    css: ['css/source/*.css'],
    scss: ['css/scss/*.scss'],
    scssWatch: ['css/scss/**'],
    js: ['js/source/*.js']
};
function errorHandler( e ){
    gutil.beep();
    gutil.log( e );
}
gulp.task('default', ['css', 'scss', 'js', 'watch']);

gulp.task('css', function () {
    gulp.src(paths.css)
        .pipe(plumber(errorHandler))
        .pipe(autoprefixer())
        .pipe(minifyCss({
            keepSpecialComments: 0
        }))
        .pipe(rename({extname: '.min.css'}))
        .pipe(gulp.dest('css/dest/'))
});


var compassOpts = {
    css: 'css/source',
    sass: 'css/scss/',
    image: 'images'
};
gulp.task('scss', function () {
    gulp.src(paths.scss)
        .pipe(plumber(errorHandler))
        .pipe(compass(compassOpts))
});

gulp.task('js', function () {
    return gulp.src(paths.js)
        .pipe(plumber(errorHandler))
        .pipe(uglify())
        .pipe(rename({extname: '.min.js'}))
        .pipe(gulp.dest('js/dest/'))
});


gulp.task('watch', function () {
    gulp.watch(paths.js, ['js']);
    gulp.watch(paths.css, ['css']);
    gulp.watch(paths.scssWatch, ['scss']);
});

gulp.task('install', ['git-check'], function () {
    return bower.commands.install()
        .on('log', function (data) {
            gutil.log('bower', gutil.colors.cyan(data.id), data.message);
        });
});

gulp.task('git-check', function (done) {
    if (!sh.which('git')) {
        console.log(
            '  ' + gutil.colors.red('Git is not installed.'),
            '\n  Git, the version control system, is required to download Ionic.',
            '\n  Download git here:', gutil.colors.cyan('http://git-scm.com/downloads') + '.',
            '\n  Once git is installed, run \'' + gutil.colors.cyan('gulp install') + '\' again.'
        );
        process.exit(1);
    }
    done();
});
