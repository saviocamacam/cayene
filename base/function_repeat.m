clear all; close all; clc;
pkg load image
pkg load signal
pkg load nan

fileID = fopen('changing2.txt','w');
for k = 1:24
    bmpFilename  = sprintf('marge (%d).bmp', k);
    [X,im1] = imread(bmpFilename);
    im1r = ind2rgb(X,im1);
    im1g=rgb2gray(im1r);
    grayImage=rgb2gray(im1);
    
    subplot(1, 2, 1);
    imshow(grayImage, []);
    title('Original Grayscale Image');
    set(gcf, 'Position', get(0,'Screensize'));

    [pixelCount grayLevels] = imhist(grayImage);

    [pixelCount grayLevels] = imhist(grayImage);
    subplot(1, 2, 2);
    bar(pixelCount);
    title('Histogram of original image');
    xlim([0 grayLevels(end)]);

    yRange = ylim;

    meanGL = sum(pixelCount .* grayLevels) / sum(pixelCount)

    st_d1=std(double(im1))

    sk1=skewness(double(im1))

    rms1=rms(im1)

    md1=mad(double(im1))

    fv1=[ meanGL,  st_d1, sk1, rms1, md1 ] 
    fprintf(fileID,'%d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, marge\n',fv1);
end


fclose(fileID);


