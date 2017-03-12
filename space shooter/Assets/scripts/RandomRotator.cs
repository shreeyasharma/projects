using UnityEngine;
using System.Collections;

public class RandomRotator : MonoBehaviour {
	public float trouble;
	private Rigidbody rb;
	// Use this for initialization
	void Start () {
		rb = GetComponent<Rigidbody> ();
		rb.angularVelocity = Random.insideUnitCircle * trouble;
	}
	
	// Update is called once per frame

}
