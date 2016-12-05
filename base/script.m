clear all; close all; clc;
pkg load image
pkg load signal
pkg load nan
[X1,im1]=imread('Train/bart001.bmp');
[X2,im2]=imread('Train/bart002.bmp');
im1r = ind2rgb(X1,im1);
im2r = ind2rgb(X2,im2);
im1g=rgb2gray(im1r); 
im2g=rgb2gray(im2r);
%% Calculating mean of im1
grayImage=rgb2gray(im1);
subplot(1, 2, 1);
imshow(grayImage, []);
title('Original Grayscale Image');
set(gcf, 'Position', get(0,'Screensize')); % Enlarge figure to full
%screen.
% Let's get its histogram.
[pixelCount grayLevels] = imhist(grayImage);
subplot(1, 2, 2);
bar(pixelCount);
title('Histogram of original image');
xlim([0 grayLevels(end)]); % Scale x axis manually.
yRange = ylim;
% Calculate the mean gray level
meanGL = sum(pixelCount .* grayLevels) / sum(pixelCount)
%% Calculating mean of  im2
grayImage2=rgb2gray(im2);
figure, subplot(1, 2, 1);
imshow(grayImage2, []);
title('Original Grayscale Image');
set(gcf, 'Position', get(0,'Screensize')); % Enlarge figure to full
%screen.
% Let's get its histogram.
[pixelCount2 grayLevels2] = imhist(grayImage2);
subplot(1, 2, 2);
bar(pixelCount2);
title('Histogram of original image');
xlim([0 grayLevels2(end)]); % Scale x axis manually.
yRange2 = ylim;
% Calculate the mean gray level
meanGL2 = sum(pixelCount2 .* grayLevels2) / sum(pixelCount2)
%% Calculating standard deviation
st_d1=std(double(im1));
st_d2=std(double(im2));
%% Calculating Skewness
sk1=skewness(double(im1));
sk2=skewness(double(im2));
%% Calculating RMS
rms1=rms(im1);
rms2=rms(im2);
%% Calculating  median absolute 
md1=mad(double(im1));
md2=mad(double(im2));
%% Contruct a feature vector
fv1=[ meanGL,  st_d1, sk1, rms1, md1 ] 
fv2= [ meanGL2, st_d2, sk2, rms2, md2 ] 