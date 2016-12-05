import cv2
import numpy as np
controlVar = {"bart":35, "lisa":13, "maggie":12, "marge":10, "homer":25}
var2 = 0
arq2 = open("Valid/hist_valid.arff", "w")
for p in controlVar.keys():
    var = 0
    arq = open("Valid/" + p + "/hist_" + p + ".txt", "w")
    for i in range (1,controlVar[p]+1):
        image = cv2.imread("Valid/" + p + "/" + p + " (%d).bmp" %i)
        raw = image.flatten()
        means = cv2.mean(image)
        means = means[:3]
        (means, stds) = cv2.meanStdDev(image)
        stats = np.concatenate([means, stds]).flatten()
        hist = cv2.calcHist([image], [0, 1, 2], None, [8, 8, 8], [0, 256, 0, 256, 0, 256])
        hist = hist.flatten()
        
        #for e in [item for sublist in means for item in sublist]:
        #   arq.write(str(e)+", ")
        if var == 0:
            var = 1        
            for i in range(len(hist)):
                arq.write("@ATTRIBUTE %d NUMERIC\n" %i)
            arq.write("@ATTRIBUTE class {bart, lisa, marge, maggie, homer}")
            arq.write("\n@DATA\n")
            
        if var2 == 0:
            var2 = 1        
            for i in range(len(hist)):
                arq2.write("@ATTRIBUTE %d NUMERIC\n" %i)
            arq2.write("@ATTRIBUTE class {bart, lisa, marge, maggie, homer}")
            arq2.write("\n@DATA\n")
            
        for f in hist: #[item for sublist in hist for item in sublist]:
            arq.write(str(f)+", ")
            arq2.write(str(f)+", ")
        
        arq.write(p + "\n")
        arq2.write(p + "\n")
    arq.close()
arq2.close()