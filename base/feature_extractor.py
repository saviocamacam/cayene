import cv2
import numpy as np
var = 0

arq = open("Train/bart/bart.txt", "w")
for i in range (1,8):
    image = cv2.imread("Train/bart/bart (%d).bmp" %i)
    raw = image.flatten()
    means = cv2.mean(image)
    means = means[:3]
    (means, stds) = cv2.meanStdDev(image)
    stats = np.concatenate([means, stds]).flatten()
    hist = cv2.calcHist([image], [0, 1, 2], None, [8, 8, 8], [0, 256, 0, 256, 0, 256])
    hist = hist.flatten()
    print hist.shape
    
    #for e in [item for sublist in means for item in sublist]:
     #   arq.write(str(e)+", ")
    if var == 0:
        var = 1        
        for i in range(len(hist)):
            arq.write("@ATTRIBUTE %d NUMERIC\n" %i)
        arq.write("@ATTRIBUTE class {bart, lisa, marge, maggie, homer}")
        arq.write("\n@DATA\n")
    for f in hist: #[item for sublist in hist for item in sublist]:
        arq.write(str(f)+", ")
    
    arq.write("bart\n")
arq.close()