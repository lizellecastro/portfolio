import React, { useState } from 'react';
import { StyleSheet, Text, View, Image, TouchableOpacity, Button } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

const Stack = createStackNavigator();

const tutors = [
  { name: 'Katherine Smith', image: 'https://img.freepik.com/free-photo/smiley-teacher-holding-tablet_23-2148668619.jpg?w=360&t=st=1697773211~exp=1697773811~hmac=2a6b1dd5d608fc4b740bec47c5302d77de904170abae6bbfd1438b4f09040a58', specialty: 'Math', tutor5star: true, ratings: '500' },
  { name: 'Melissa Stone', image: 'https://img.freepik.com/free-photo/vertical-shot-pleased-curly-haired-female-teacher-prepares-lessons-home-holds-red-textbook-poses-against-desktop_273609-39238.jpg?size=626&ext=jpg&ga=GA1.1.1014059599.1696799556&semt=sph', specialty: 'Computer Science', tutor5star: true, ratings: '1023'},
  { name: 'Robert Jameson', image: 'https://img.freepik.com/free-photo/outgoing-lecturer-standing-rostrum-explaining-material_23-2148201007.jpg?w=740&t=st=1697774029~exp=1697774629~hmac=cedc1bdb6f64453d33fce4b9fe75a04fb930e79682e7f267cbf1f610ec09615a', specialty: 'Chemistry', tutor5star: true, ratings: '870' },
  { name: 'Kyle Roman', image: 'https://cdn.sanity.io/images/4wurd6lm/production/255f680009d3726cb823e59a842fa87b95cd9789-1852x1501.jpg?w=1852&h=1501&auto=format', specialty: 'Physics', tutor5star: true, ratings: '600' },
  { name: 'Jane Dillard', image: 'https://cdn.aarp.net/content/dam/aarp/about_aarp/nrta/2016/1140-nrta-overall-banner-teacher-portrait.jpg', specialty: 'Biology', tutor5star: true, ratings: '2050'},
  // Add more tutors as needed
];

const Tutors = ({ navigation }) => {
  return (
    <View style={{ alignItems: 'center' }}>
      <Button title="Go Back" onPress={() => navigation.goBack()} />
      <Image
        source={{ uri: 'https://img.freepik.com/free-vector/stem-logo-with-kids-cartoon-character-education-icon-elements_1308-62171.jpg?size=626&ext=jpg&ga=GA1.2.1014059599.1696799556' }}
        style={{ width: 300, height: 150 }}
      />
      <Text style={styles1.titleText}>Top Tutors</Text>
      {tutors.map((tutor, index) => (
        <View key={index} style={styles1.tutorContainer}>
          <Image source={{ uri: tutor.image }} style={styles1.tutorImage} />
          <View style={styles1.tutorInfo}>
            <Text style={styles1.tutorName}>{tutor.name}</Text>
            <Text style={styles1.tutorSpecialty}>{tutor.specialty}</Text>
            {tutor.tutor5star && (
              <View style={styles1.starsContainer}>
                {[...Array(5)].map((_, i) => (
                  <Image
                    key={i}
                    source={{ uri: 'https://m.media-amazon.com/images/I/616DSJhPyYL.jpg'}} 
                    style={styles1.starImage}
                  />
                ))}
                <Text style={styles1.starText}>({tutor.ratings})</Text>
              </View>
            )}
            <TouchableOpacity style={styles1.openButton}>
              <Text style={styles1.openButtonText}>Open</Text>
            </TouchableOpacity>
          </View>
        </View>
      ))}
    </View>
  );
};

const styles1 = StyleSheet.create({
  tutorContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    alignSelf: 'flex-start',
    marginBottom: 20,
  },
  titleText: {
    fontWeight: 'bold',
    fontSize: 20,
    marginTop: 10,
    marginBottom: 10,
  },
  tutorImage: {
    width: 100,
    height: 100,
    borderRadius: 25,
    marginRight: 10,
  },
  tutorInfo: {
    marginLeft: 10,
    justifyContent: 'center',
    width: '60%',
  },
  tutorName: {
    fontSize: 18,
    fontWeight: 'bold',
    marginTop: 10,
  },
  tutorSpecialty: {
    textAlign: 'center',
    alignSelf: 'flex-start',
    fontSize: 12,
  },
  starsContainer: {
    flexDirection: 'row',
    marginTop: 5,
  },
  starImage: {
    width: 10,
    height: 10,
    marginHorizontal: 2,
  },
  starText: {
    fontSize: 12,
    marginLeft: 5,
  },
  openButton: {
    backgroundColor: 'lightgreen',
    padding: 10,
    borderRadius: 5,
    marginLeft: 'auto',
  },
  openButtonText: {
    color: 'white',
  },
});


const Calculator = ({ navigation }) => {
  const [displayValue, setDisplayValue] = useState('0');
  const [operator, setOperator] = useState(null);
  const [firstValue, setFirstValue] = useState('');

  const handleNumberInput = (num) => {
    if (displayValue === '0') {
      setDisplayValue(num.toString());
    } else {
      setDisplayValue(displayValue + num);
    }
  };

  const handleOperatorInput = (operator) => {
    setOperator(operator);
    setFirstValue(displayValue);
    setDisplayValue('0');
  };

  const handleEqual = () => {
    let result = 0;
    const num1 = parseFloat(firstValue);
    const num2 = parseFloat(displayValue);

    if (operator === '+') {
      result = num1 + num2;
      setDisplayValue((result).toString());
    } else if (operator === '-') {
      result = num1 - num2;
      setDisplayValue((result).toString());
    } else if (operator === '*') {
      result = num1 * num2;
      setDisplayValue((result).toString());
    } else if (operator === '/') {
      result = num1/num2;
      setDisplayValue((result).toString());
    }

    setOperator(null);
    setFirstValue('');
  };

  const handleClear = () => {
    setDisplayValue('0');
    setOperator(null);
    setFirstValue('');
  };

  return (
    <View style={styles.container}>
      <Button
        title="Go to Tutors list"
        onPress={() => navigation.navigate('Tutors')}
      />
      <Image source = {{uri: 'https://img.freepik.com/free-vector/stem-logo-with-kids-cartoon-character-education-icon-elements_1308-62171.jpg?size=626&ext=jpg&ga=GA1.2.1014059599.1696799556'}}
      style = {{width: 300, height: 150}}
      />
          <View style={styles.displayContainer}>
              <Text style={styles.displayText}>
                  {displayValue}
              </Text>
          </View>
          <View style={styles.buttonContainer}>
              <View style={styles.row}>
                  <TouchableOpacity
                      style={styles.button}
                      onPress={() => handleNumberInput(7)}
                  >
                      <Text style={styles.buttonText}>7</Text>
                  </TouchableOpacity>
                  <TouchableOpacity
                      style={styles.button}
                      onPress={() => handleNumberInput(8)}
                  >
                      <Text style={styles.buttonText}>8</Text>
                  </TouchableOpacity>
                  <TouchableOpacity
                      style={styles.button}
                      onPress={() => handleNumberInput(9)}
                  >
                      <Text style={styles.buttonText}>9</Text>
                  </TouchableOpacity>
                  <TouchableOpacity
                      style={[styles.button, styles.operatorButton]}
                      onPress={() => handleOperatorInput('*')}
                  >
                      <Text style={[
                          styles.buttonText,
                          styles.operatorButtonText
                      ]}>
                          ×
                      </Text>
                  </TouchableOpacity>
              </View>
              <View style={styles.row}>
                  <TouchableOpacity
                      style={styles.button}
                      onPress={() => handleNumberInput(4)}
                  >
                      <Text style={styles.buttonText}>4</Text>
                  </TouchableOpacity>
                  <TouchableOpacity
                      style={styles.button}
                      onPress={() => handleNumberInput(5)}
                  >
                      <Text style={styles.buttonText}>5</Text>
                  </TouchableOpacity>
                  <TouchableOpacity
                      style={styles.button}
                      onPress={() => handleNumberInput(6)}
                  >
                      <Text style={styles.buttonText}>6</Text>
                  </TouchableOpacity>
                  <TouchableOpacity
                      style={[styles.button, styles.operatorButton]}
                      onPress={() => handleOperatorInput('/')}
                  >
                      <Text style={[
                          styles.buttonText,
                          styles.operatorButtonText
                      ]}>
                          ÷
                      </Text>
                  </TouchableOpacity>
              </View>
              <View style={styles.row}>
                  <TouchableOpacity
                      style={styles.button}
                      onPress={() => handleNumberInput(1)}
                  >
                      <Text style={styles.buttonText}>1</Text>
                  </TouchableOpacity>
                  <TouchableOpacity
                      style={styles.button}
                      onPress={() => handleNumberInput(2)}
                  >
                      <Text style={styles.buttonText}>2</Text>
                  </TouchableOpacity>
                  <TouchableOpacity
                      style={styles.button}
                      onPress={() => handleNumberInput(3)}
                  >
                      <Text style={styles.buttonText}>3</Text>
                  </TouchableOpacity>
                  <TouchableOpacity
                      style={[styles.button, styles.operatorButton]}
                      onPress={() => handleOperatorInput('-')}
                  >
                      <Text style={[
                          styles.buttonText,
                          styles.operatorButtonText
                      ]}>
                          −
                      </Text>
                  </TouchableOpacity>
              </View>
              <View style={styles.row}>
                  <TouchableOpacity
                      style={[styles.button, styles.zeroButton]}
                      onPress={() => handleNumberInput(0)}
                  >
                      <Text style={[
                          styles.buttonText,
                          styles.zeroButtonText
                      ]}>
                          0
                      </Text>
                  </TouchableOpacity>
                  <TouchableOpacity
                      style={[styles.button, styles.operatorButton]}
                      onPress={() => handleOperatorInput('+')}
                  >
                      <Text style={[
                          styles.buttonText,
                          styles.operatorButtonText
                      ]}>
                          +
                      </Text>
                  </TouchableOpacity>
                  <TouchableOpacity
                      style={styles.equalButton}
                      onPress={handleEqual}
                  >
                      <Text style={styles.equalButtonText}>=</Text>
                  </TouchableOpacity>
              </View>
              <TouchableOpacity
                  style={styles.clearButton}
                  onPress={handleClear}>
                  <Text style={styles.clearButtonText}>C</Text>
              </TouchableOpacity>
          </View>
      </View>
);
};

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Calculator">
        <Stack.Screen name="Calculator" component={Calculator} />
        <Stack.Screen name="Tutors" component={Tutors} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#7fffd4',
    alignItems: 'center',
    justifyContent: 'center',
  },

  displayContainer: {
    flex: 1,
    justifyContent: 'flex-end',
    padding: 20,
    paddingLeft: 35,
    paddingRight: 35,
  },
  displayText: {
    backgroundColor: '#c0c0c0',
    padding: 10,
    marginTop: 10,
    borderRadius: 50,
    fontSize: 60,
    color: '#9932cc',
  },
  buttonContainer: {
    flex: 4,
    width: '90%',
  },
  row: {
    flex: 1,
    marginBottom: 5,
    justifyContent: 'space-between',
    flexDirection: 'row',
  },
  button: {
    flex: 1,
    borderRadius: 100,
    backgroundColor: '#87ceeb',
    alignItems: 'center',
    justifyContent: 'center',
    elevation: 5,
    margin: 2,
    padding: 15,
  },
  buttonText: {
    fontSize: 30,
    color: '#333',
  },
  zeroButton: {
    flex: 1,
    paddingLeft: 35,
    paddingRight: 35,
  },
  zeroButtonText: {
    marginLeft: 10,
  },
  operatorButton: {
    backgroundColor: '#333',
  },
  operatorButtonText: {
    color: '#fffafa',
  },
  equalButton: {
    flex: 1,
    borderRadius: 50,
    backgroundColor: '#333',
    alignItems: 'center',
    justifyContent: 'center',
    elevation: 3,
  },
  equalButtonText: {
    fontSize: 32,
    color: '#fff',
  },
  clearButton: {
    borderRadius: 50,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#d2b48c',
    marginTop: 10,
    elevation: 3,
    padding: 10,
  },
  clearButtonText: {
    fontSize: 30,
    color: '#333',
  },
});