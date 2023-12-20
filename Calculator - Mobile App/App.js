import React, { useState } from 'react';
import { StyleSheet, Text, View, Image, TouchableOpacity }
	from 'react-native';

export default function App() {

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
			
			<Image source = {{uri: 'https://img.freepik.com/free-vector/stem-logo-with-kids-cartoon-character-education-icon-elements_1308-62171.jpg?size=626&ext=jpg&ga=GA1.2.1014059599.1696799556'}}
			style = {{width: 400, height: 200}}
			/>
			<Text style={styles.title}>React Native - Calculator App</Text>
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
}

// Styles
const styles = StyleSheet.create({

	container: {
		flex: 1,
		backgroundColor: '#7fffd4',
		alignItems: 'center',
		justifyContent: 'center',
	},
	title: {
		top: 20,
		marginTop: 10,
		paddingVertical: 20,
		borderWidth: 4,
		borderColor: '#20232a',
		borderRadius: 6,
		backgroundColor: '#dc143c',
		color: '#20232a',
		textAlign: 'center',
		fontSize: 30,
		fontWeight: 'bold',
	  },
	text: {
		top: 100,
		fontSize: 40,
		color: '#000',
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
		fontSize: 40,
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

