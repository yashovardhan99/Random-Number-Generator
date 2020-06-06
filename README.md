# Random-Number-Generator
A simple random number generator/dice android app built for #30DaysOfKotlin using MVVM architecture

The app allows you to roll a dice/roll 2 dice and spin a number between a user-selected range.

## Architecture followed
This app was mainly built to demonstate my understanding of MVVM architecture and the power of Kotlin. Some key points are:-

* Single Activity, Multiple Fragments
* Safe Args plugin for fragment navigation
* Seperation of concerns - ViewModel and Fragment are well-seperated
* ViewModel is well unaware of presence of any views
* 2 way data binding to avoid boilerplate code
* Dark Material design style
* Use of string and dimen resources
* Mutable data kept private within viewmodel. Read-only data is exposed.
* Extensive use of LiveData
* Coroutines used in ViewModel

## License
Copyright 2020 Yashovardhan Dhanania

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
