# TextureClassification-GLCM-NB

Texture Classification using Naive Bayes and GLCM for feature extraction
Dataset can be found [here](http://www-cvr.ai.uiuc.edu/ponce_grp/data/), at the ***Texture Database*** section.

## How to Use
### Texture listed
Class of Texture is located at
```
id.web.ard.textureclassification.glcmnb.model.TextureClass.java
```
you can add or remove the class that will be used.

### Naming the Training Data
You must add the **[dot]index of class** at the end of the file name, example:

* wood1**.1**.jpg
* wood2**.1**.jpg
* wood3**.1**.jpg
* brick1**.2**.jpg
* brick2**.2**.jpg
* brick3**.2**.jpg
* and so on...