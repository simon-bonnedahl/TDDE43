import { StatusBar } from 'expo-status-bar';
import { Component, useState} from 'react';
import MaterialCommunityIcons from 'react-native-vector-icons/Entypo';
import MaterialCommunityIcons2 from 'react-native-vector-icons/Feather';
import { StyleSheet, View, Image} from 'react-native';


export default function App() {

  var defaultImageSize = 300;
  const [imageId, setImageId] = useState(1);
  const [imageSize, setImageSize] = useState(defaultImageSize);
  const [imageRotation, setImageRotation] = useState(0);

  generateRandomNubmber = function(previous, max){
    while(true){
      let randomNumber = Math.floor(Math.random()*max);
      if(randomNumber != previous){
        return randomNumber;
      }
    }
  }

  return (
    <View style={styles.container}>
       <Image  
          style={{transform: [{ rotate: imageRotation + 'deg'}]}} 
          testID='image'
          source= {{
            testID:'image-style',
            rotate: imageRotation,
            width: imageSize,
            height: imageSize,
            uri: "https://picsum.photos/id/" + imageId + "/" + defaultImageSize}}/>
        <Image />
        <View style={styles.icons}>
        <MaterialCommunityIcons
            name="cycle"
            testID='refresh-button'
            size={40}
            color={'#000000'} 
            onPress={() => setImageId(generateRandomNubmber(imageId, 300))}/>
        <MaterialCommunityIcons
            name="plus" 
            testID='plus-button'
            color={'#000000'} 
            size={40}
            onPress={() => setImageSize(imageSize+10)}/>
           
            <MaterialCommunityIcons
            name="minus" 
            testID='minus-button'
            color={'#000000'} 
            size={40}
            onPress={() => setImageSize(imageSize-10)}/>
           
          
            <MaterialCommunityIcons2
            name="rotate-ccw" 
            testID='rotate-left-button'
            color={'#000000'} 
            size={40}
            onPress={() => setImageRotation(imageRotation-90)}/>

            <MaterialCommunityIcons2
            name="rotate-cw"
            testID='rotate-right-button' 
            color={'#000000'} 
            size={40}
            onPress={() => setImageRotation(imageRotation+90)}/>
          
           
           <StatusBar/>
        </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#999'
  },icons:{
    flexDirection: 'row'
  }
 
});
