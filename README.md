# Dependency Injection with Dagger Hilt for Retrofit

This is an implementation of the Dagger Hilt usage reference [here.](https://github.com/Sendiko/dagger-hilt-reference)

### How it used
---

I use the Dagger Hilt to provide Retrofit, OkHttpClient, and ApiService.
```kotlin
  @Module
  @Installin(SingletonComponent::class)
  object AppModule {
    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient{
        return OkHttpClient.Builder().addInterceptor { chain ->
            chain.proceed(chain.request().newBuilder().also {
                it.addHeader("Accept", "application/json")
            }.build())
        }.also {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            it.addInterceptor(logging)
        }
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.9:8000/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
  }
```
Singleton is used to make sure that there are only 1 instance are created in on lifecycle. since i provide the retrofit instace in the Hilt Module, i don't need to create an instance in the ApiConfig.kt, those there is only ApiService.kt.
```kotlin
interface ApiService {

    @GET("tickets")
    fun getTickets(): Call<GetTicketsResponse>

    @GET("concerts")
    fun getConcerts(): Call<GetConcertsResponse>

}
```

In this case, i do not create a repository for the services, those making the only class that use ```@Inject``` annotation are the ViewModel.

```kotlin
class HomeScreenViewModel @Inject constructor(
    private val apiService: ApiService
)  
```

and with dagger hilt i can also just add ```@HiltViewModel``` to mark my viewmodel as hilt viewmodel for easier injection.
```kotlin
  @HiltViewModel
  class HomeScreenViewModel @Inject constructor(
    private val apiService: ApiService
  ): ViewModel()
  ---
  val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
  Surface(
    modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colorScheme.background
      ) {
        HomeScreen(
          state = homeScreenViewModel.state.collectAsState().value,
          onEvents = homeScreenViewModel::onEvent
        )
      }
```

### The Injection Flow
---

So the flow of the dependency injection with dagger hilt that i understand is: 
```
AppModule define, and provide dependencies 
  -> classes that need the dependency can call @Inject to get the dependency(ies)
    -> and define @HiltViewModel to ViewModel to let easier instance creation
```
