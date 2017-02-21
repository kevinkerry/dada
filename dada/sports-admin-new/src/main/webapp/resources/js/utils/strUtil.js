/**
 * 
 */

function imgPath(img){
	if (img != '' && img != null) {
		if (!isContains(img, "http://")) {
			img =  "http://image.dadasports.cn"  + img;
		}
	}
	return img;
}

function isContains(str, substr) {
	return str.indexOf(substr) >= 0;
}