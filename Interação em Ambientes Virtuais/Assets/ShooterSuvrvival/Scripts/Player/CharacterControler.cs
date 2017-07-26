using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CharacterControler : MonoBehaviour
{

    //controlar a velocidade do boneco
	public float speed;
    Rigidbody playerRigiBody;
    float translation, straffe;
	bool isJumping;
	private float timer,timeBetweenJumps;
	private float walkingSoundTimer, walkingSoundGap;
	public float jumpingForce;
	private AudioSource[] audio;

    // Use this for initialization
    void Start()
    {
        //o cursor apenas fica na frame do jogo e nunca fora
        Cursor.lockState = CursorLockMode.Locked;
        playerRigiBody = GetComponent<Rigidbody>();
        translation = 0f;
        straffe = 0f;
		playerRigiBody.mass = 2f;
		timeBetweenJumps = 0.7f;
		walkingSoundGap = 0.40f;
		//jumpingForce = 120f;
//		speed = 10.0f;
		audio = GetComponents<AudioSource> ();

    }

    //
    private void FixedUpdate()
    {
        //é melhor usar o fixedupdate para acções em RigidBodys
        getInputs();
        Move();
    }

	private void Jumping (){
		audio [1].Stop ();
		timer = 0f;
		Vector3 jump = new Vector3 (0,20,0);
		playerRigiBody.AddForce (jump * jumpingForce);
		isJumping = false;

	}

    private void getInputs()
    {	
		walkingSoundTimer += Time.deltaTime;
        //forward and back movements
        translation = Input.GetAxisRaw("Vertical") * speed;
        //sideways movements
        straffe = Input.GetAxisRaw("Horizontal") * speed;
		//time scale == 0 GAME PAUSED
		isJumping = Input.GetKey (KeyCode.Space);

		if((Input.GetAxisRaw("Vertical")!=0 || Input.GetAxisRaw("Horizontal")!=0) && walkingSoundTimer >= walkingSoundGap){
			audio [1].Play ();
			walkingSoundTimer = 0f;
		}
    }

    private void Move()
    {	
        //multiplica-los com o delta time, smoothen and keeps in update syncronization
        float movementH = straffe * Time.deltaTime;
        float movementV = translation * Time.deltaTime;

        playerRigiBody.transform.Translate(movementH, 0f, movementV);

    }

	private void Update(){
		timer += Time.deltaTime;
		if(isJumping && timer >= timeBetweenJumps && Time.timeScale != 0){
			Jumping ();
		}

		//mudança da gravidade do player
		//aplicação da força da gravidade
		playerRigiBody.AddForce (Physics.gravity*3f, ForceMode.Acceleration);
	}
  

}
